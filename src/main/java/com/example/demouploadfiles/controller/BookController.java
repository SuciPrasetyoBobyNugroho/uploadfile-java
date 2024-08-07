package com.example.demouploadfiles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demouploadfiles.dto.ResponseData;
import com.example.demouploadfiles.entity.Book;
import com.example.demouploadfiles.service.BookService;
import com.example.demouploadfiles.utility.CSVHelper;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> findAllBook() {

        ResponseData response = new ResponseData();

        try {
            List<Book> books = bookService.findAll();
            response.setStatus(true);
            response.getMesssages().add("Get all books");
            response.setPayload(books);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.getMesssages().add("Could not get books: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

        ResponseData response = new ResponseData();

        if (!CSVHelper.hasCSVFormat(file)) {
            response.setStatus(false);
            response.getMesssages().add("please upload file CSV file");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            List<Book> books = bookService.save(file);
            response.setStatus(true);
            response.getMesssages().add("Succes uploadfile: " + file.getOriginalFilename());
            response.setPayload(books);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(false);
            response.getMesssages().add("could not upload file: " + file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        }
    }
}
