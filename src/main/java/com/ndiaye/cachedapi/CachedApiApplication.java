package com.ndiaye.cachedapi;

import com.ndiaye.cachedapi.entity.Book;
import com.ndiaye.cachedapi.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CachedApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachedApiApplication.class, args);
	}

}
