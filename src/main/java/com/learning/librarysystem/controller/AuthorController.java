package com.learning.librarysystem.controller;


import com.learning.librarysystem.dto.Author.AuthorDto;
import com.learning.librarysystem.dto.Author.CreateAuthorDto;
import com.learning.librarysystem.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping(path = "")
    public AuthorDto createAuthor(@RequestBody @Valid CreateAuthorDto newAuthorDto) {
        return authorService.createAuthor(newAuthorDto);
    }

    @GetMapping(path="{id}")
    public AuthorDto getAuthorById(@PathVariable("id") String id) {
        return authorService.getAuthorById(id);
    }

    @DeleteMapping(path="{id}")
    public boolean deleteAuthorById(@PathVariable("id") String id) {
        return authorService.deleteAuthorById(id);
    }



}
