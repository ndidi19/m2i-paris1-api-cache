package com.ndiaye.cachedapi.service.impl;

import com.ndiaye.cachedapi.dto.BookResponseDto;
import com.ndiaye.cachedapi.dto.UpdateBookDto;
import com.ndiaye.cachedapi.entity.Book;
import com.ndiaye.cachedapi.exception.BookNotFoundException;
import com.ndiaye.cachedapi.repository.BookRepository;
import com.ndiaye.cachedapi.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable("books")
    @Override
    public List<Book> getAllBooks() {
        log.info("Called 'getAllBooks' from BookService");
        return bookRepository.findAll();
    }

    @Cacheable(value = "books", key = "#id")
    @Override
    public BookResponseDto getBookById(Long id) {
        log.info("Called 'getBookById' from BookService => id : " + id);
        Book retrievedBook = bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException("Book not found for id : " + id)
        );
        BookResponseDto book = new BookResponseDto();
        book.setId(retrievedBook.getId());
        book.setTitle(retrievedBook.getTitle());
        book.setAuthor(retrievedBook.getAuthor());
        book.setYear(retrievedBook.getYear());
        return book;
    }

    @CachePut(value = "books", key = "#id")
    @Override
    public BookResponseDto updateBook(Long id, UpdateBookDto updateBookDto) {
        log.info("Called 'updateBook' from BookService => id : " + id);
        Book retrievedBook = bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException("Book not found for id : " + id)
        );
        retrievedBook.setYear(updateBookDto.getYear());
        retrievedBook.setCountry(updateBookDto.getCountry());
        bookRepository.save(retrievedBook);
        BookResponseDto book = new BookResponseDto();
        book.setId(retrievedBook.getId());
        book.setTitle(retrievedBook.getTitle());
        book.setAuthor(retrievedBook.getAuthor());
        book.setYear(retrievedBook.getYear());
        return book;
    }


}
