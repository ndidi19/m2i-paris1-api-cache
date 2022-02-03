package com.ndiaye.cachedapi;

import com.ndiaye.cachedapi.entity.Book;
import com.ndiaye.cachedapi.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CachedApiApplication implements CommandLineRunner {

	private final BookRepository bookRepository;

	public CachedApiApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CachedApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book = new Book(
				"Harry Potter", "J. K. Rowling", 1997, "UK"
		);
		Book book2 = new Book(
				"Lord of the Rings", "J. R. R. Tolkien", 1954, "UK"
		);
		Book book3 = new Book(
				"Artemis Fowl", "Eoin Colfer", 2001, "Irish"
		);
		List<Book> bookList = List.of(book, book2, book3);
		bookRepository.saveAll(bookList);
	}
}
