package com.ndiaye.cachedapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private int year;
    private String country;

    public Book(String title, String author, int year, String country) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.country = country;
    }
}
