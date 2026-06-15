package com.learning.librarysystem.service;

import com.learning.librarysystem.dto.Author.AuthorDto;
import com.learning.librarysystem.dto.Author.CreateAuthorDto;
import com.learning.librarysystem.entity.Author;
import com.learning.librarysystem.repository.AuthorRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorDto createAuthor(CreateAuthorDto newAuthorDto) {
        Author author = modelMapper.map(newAuthorDto, Author.class);
        authorRepository.save(author);
        return modelMapper.map(author, AuthorDto.class);
    }

    public AuthorDto getAuthorById(String id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return modelMapper.map(author, AuthorDto.class);
    }

    public boolean deleteAuthorById(String id) {
        boolean author = authorRepository.existsById(id);
        if(!author){
            return false;
        }

        authorRepository.deleteById(id);
        return true;
    }
}
