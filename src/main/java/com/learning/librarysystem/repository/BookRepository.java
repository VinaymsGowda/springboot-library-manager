package com.learning.librarysystem.repository;

import com.learning.librarysystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {


    public List<Book> findAllBooksByTitleOrCreatedAtAfter(String title, LocalDateTime createdAt);
}