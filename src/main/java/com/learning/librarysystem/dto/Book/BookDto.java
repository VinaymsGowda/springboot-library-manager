package com.learning.librarysystem.dto.Book;
import com.learning.librarysystem.dto.Author.AuthorDto;
import com.learning.librarysystem.entity.Author;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class BookDto {


    private String id;


    private String title;


    private String description;


    private int price;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;


    private String authorId;

//    private AuthorDto author;
}
