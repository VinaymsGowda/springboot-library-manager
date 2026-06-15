package com.learning.librarysystem.controller;

import com.learning.librarysystem.dto.Book.BookDto;
import com.learning.librarysystem.dto.Book.CreateBookDto;
import com.learning.librarysystem.dto.Book.UpdateBookDto;
import com.learning.librarysystem.repository.BookRepository;
import com.learning.librarysystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/book")
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService;

    @PostMapping
    public BookDto createBook(@RequestBody @Valid CreateBookDto bookDto){
        return bookService.createBook(bookDto);
    }

    @GetMapping
    public List<BookDto> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDateTime createdAt){
        return bookService.findAllBooks(title,createdAt);
    }

    @GetMapping(path = "{id}")
    public BookDto getBookById(@PathVariable("id") String id){
        return bookService.findBookById(id);
    }


    @PatchMapping(path = "{id}")
    public BookDto updateBook(@PathVariable("id") String id, @RequestBody @Valid UpdateBookDto bookDto){
        return bookService.updateBookById(id,bookDto);
    }

    @DeleteMapping(path="{id}")
    public boolean deleteBookById(@PathVariable("id") String id){
        return bookService.deleteBookById(id);
    }




}
