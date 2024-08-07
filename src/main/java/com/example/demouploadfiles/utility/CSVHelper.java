package com.example.demouploadfiles.utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.demouploadfiles.entity.Book;

public class CSVHelper {

    private static final String TYPE = "text/csv";
    // private static String[] HEADERS = { "id", "title", "description", "price" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Book> csvToBooks(InputStream is) {
        try {
            BufferedReader filReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser parser = new CSVParser(filReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<Book> books = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = parser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Book book = new Book();
                book.setId(Long.parseLong(csvRecord.get("Id")));
                book.setTitle(csvRecord.get("title"));
                book.setDescription(csvRecord.get("description"));
                book.setPrice(Double.parseDouble(csvRecord.get("price")));
                books.add(book);
            }
            parser.close();
            return books;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file : " + e.getMessage());
        }
    }

}
