package com.ndiaye.cachedapi.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
