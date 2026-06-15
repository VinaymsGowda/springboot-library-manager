package com.learning.librarysystem.service;

import com.learning.librarysystem.dto.Book.BookDto;
import com.learning.librarysystem.dto.Book.CreateBookDto;
import com.learning.librarysystem.dto.Book.UpdateBookDto;
import com.learning.librarysystem.entity.Author;
import com.learning.librarysystem.entity.Book;
import com.learning.librarysystem.repository.AuthorRepository;
import com.learning.librarysystem.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;


    public BookDto createBook(CreateBookDto newBook){
        Author author = authorRepository.findById(newBook.getAuthor()).orElseThrow();

        Book bookEntity=modelMapper.map(newBook,Book.class);
        bookEntity.setAuthor(author);
        Book savedBookEntity = bookRepository.save(bookEntity);
        return modelMapper.map(bookEntity, BookDto.class);
    }

    public List<BookDto> findAllBooks(String title, LocalDateTime createdAt){
        List<Book> books=bookRepository.findAllBooksByTitleOrCreatedAtAfter(title,createdAt);

        List<BookDto> booksDto=books.stream().map((element) -> modelMapper.map(element, BookDto.class)).toList();
        return booksDto;

    }

    public BookDto findBookById(String bookId){
        Book book=bookRepository.findById(bookId).orElseThrow();

        return modelMapper.map(book,BookDto.class);
    }

    public BookDto updateBookById(String bookId, UpdateBookDto bookDto){
        Book book=bookRepository.findById(bookId).orElseThrow();

        if(bookDto.getAuthor()!=null){
            Author author=authorRepository.findById(bookDto.getAuthor()).orElseThrow();
            book.setAuthor(author);
        }

        modelMapper.map(bookDto,book);

        bookRepository.save(book);
        return modelMapper.map(book,BookDto.class);
    }

    public boolean deleteBookById(String bookId){
        boolean bookExists=bookRepository.existsById(bookId);
        if(!bookExists){
            return false;
        }

        bookRepository.deleteById(bookId);
        return true;
    }
}
