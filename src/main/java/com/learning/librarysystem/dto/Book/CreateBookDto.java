package com.learning.librarysystem.dto.Book;

import com.learning.librarysystem.entity.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class CreateBookDto {



    @NotBlank
    private String title;

    private String description;

    @Positive
    private int price;

    @NotBlank
    private String author;
}
