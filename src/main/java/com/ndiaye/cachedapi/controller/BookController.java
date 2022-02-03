package com.ndiaye.cachedapi.controller;

import com.ndiaye.cachedapi.dto.BookResponseDto;
import com.ndiaye.cachedapi.dto.UpdateBookDto;
import com.ndiaye.cachedapi.entity.Book;
import com.ndiaye.cachedapi.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class BookController {

    private final static Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        log.info("Called 'getAllBooks' from BookController");
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        log.info("Called 'getBookById' from BookController => id : " + id);
        return bookService.getBookById(id);
    }

    @PutMapping("/books/{id}")
    public BookResponseDto updateBookById(@PathVariable Long id, @RequestBody UpdateBookDto updateBookDto) {
        return bookService.updateBook(id, updateBookDto);
    }
}
