package com.learning.librarysystem.dto.Book;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateBookDto {

    private String title;

    private String description;

    @Positive
    private Integer price;

    private String author;
}
