package com.example.demouploadfiles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demouploadfiles.entity.Book;
import com.example.demouploadfiles.repository.BookRepo;
import com.example.demouploadfiles.utility.CSVHelper;

import jakarta.transaction.Transactional;

@Service("bookService")
@Transactional
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> save(MultipartFile file) {
        try {
            List<Book> books = CSVHelper.csvToBooks(file.getInputStream());
            return bookRepo.saveAll(books);
        } catch (Exception e) {
            throw new RuntimeException("fail to store csv data : " + e.getMessage());
        }
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }
}
