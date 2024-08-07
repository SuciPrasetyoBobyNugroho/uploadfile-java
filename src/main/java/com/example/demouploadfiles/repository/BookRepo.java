package com.example.demouploadfiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demouploadfiles.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
    
}
