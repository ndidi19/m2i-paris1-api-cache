package com.ndiaye.cachedapi.service;

import com.ndiaye.cachedapi.dto.BookResponseDto;
import com.ndiaye.cachedapi.dto.CreateBookDto;
import com.ndiaye.cachedapi.dto.UpdateBookDto;
import com.ndiaye.cachedapi.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();

    BookResponseDto getBookById(Long id);

    BookResponseDto updateBook(Long id, UpdateBookDto updateBookDto);

    void deleteBookById(Long id);

    void deleteAllBooks();

    BookResponseDto createBook(CreateBookDto createBookDto);
}
