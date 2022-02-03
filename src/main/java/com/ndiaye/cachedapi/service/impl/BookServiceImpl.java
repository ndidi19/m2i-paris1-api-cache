package com.ndiaye.cachedapi.service.impl;

import com.ndiaye.cachedapi.dto.BookResponseDto;
import com.ndiaye.cachedapi.dto.CreateBookDto;
import com.ndiaye.cachedapi.dto.UpdateBookDto;
import com.ndiaye.cachedapi.entity.Book;
import com.ndiaye.cachedapi.exception.BookAlreadyExistsException;
import com.ndiaye.cachedapi.exception.BookNotFoundException;
import com.ndiaye.cachedapi.repository.BookRepository;
import com.ndiaye.cachedapi.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_NOT_FOUND_MSG = "Book not found for id : ";
    private static final String BOOK_ALREADY_EXISTS_MSG = "Book already exists for title : ";

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable("allBooks")
    @Override
    public List<BookResponseDto> getAllBooks() {
        log.info("Called 'getAllBooks' from BookService");
        List<Book> bookList = bookRepository.findAll();
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        for (Book b : bookList) {
            bookResponseDtoList.add(bookToBookResponseDto(b));
        }
        return bookResponseDtoList;
    }

    @Cacheable(value = "books", key = "#id")
    @Override
    public BookResponseDto getBookById(Long id) {
        log.info("Called 'getBookById' from BookService => id : " + id);
        Book retrievedBook = getBookEntityById(id);
        return bookToBookResponseDto(retrievedBook);
    }

    @Caching(evict = { @CacheEvict(value = "allBooks", allEntries = true)
            }, put = { @CachePut(value = "books", key = "#id") })
    @Override
    public BookResponseDto updateBook(Long id, UpdateBookDto updateBookDto) {
        log.info("Called 'updateBook' from BookService => id : " + id);
        Book retrievedBook = getBookEntityById(id);
        retrievedBook.setYear(updateBookDto.getYear());
        retrievedBook.setCountry(updateBookDto.getCountry());
        bookRepository.save(retrievedBook);
        return bookToBookResponseDto(retrievedBook);
    }

    @Caching(evict = { @CacheEvict(value = "allBooks", allEntries = true), @CacheEvict(value = "books", key = "#id")})
    @Override
    public void deleteBookById(Long id) {
        log.info("Called 'deleteBookById' from BookService => id : " + id);
        Book retrievedBook = getBookEntityById(id);
        bookRepository.delete(retrievedBook);
    }

    @Caching(evict = { @CacheEvict(value = "allBooks", allEntries = true), @CacheEvict(value = "books", allEntries = true)})
    @Override
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    @CacheEvict(value = "allBooks", allEntries = true)
    @Override
    public BookResponseDto createBook(CreateBookDto createBookDto) {
        String title = createBookDto.getTitle();
        Optional<Book> existingBook = bookRepository.findByTitle(title);
        if (existingBook.isPresent()) {
            log.error(BOOK_ALREADY_EXISTS_MSG + title);
            throw new BookAlreadyExistsException(BOOK_ALREADY_EXISTS_MSG + title);
        }
        Book book = new Book(
                createBookDto.getTitle(),
                createBookDto.getAuthor(),
                createBookDto.getYear(),
                createBookDto.getCountry()
        );
        Book savedBook = bookRepository.save(book);
        return bookToBookResponseDto(savedBook);
    }

    private Book getBookEntityById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(BOOK_NOT_FOUND_MSG + id)
        );
    }

    private BookResponseDto bookToBookResponseDto(Book b)
    {
        BookResponseDto book = new BookResponseDto();
        book.setCountry(b.getCountry());
        book.setTitle(b.getTitle());
        book.setAuthor(b.getAuthor());
        book.setYear(b.getYear());
        return book;
    }


}
