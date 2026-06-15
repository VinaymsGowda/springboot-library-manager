package com.learning.librarysystem.dto.Author;

import com.learning.librarysystem.dto.Book.BookDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private List<BookDto> books=new ArrayList<>();
}
