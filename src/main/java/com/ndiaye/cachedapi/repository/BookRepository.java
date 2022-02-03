package com.ndiaye.cachedapi.repository;

import com.ndiaye.cachedapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
