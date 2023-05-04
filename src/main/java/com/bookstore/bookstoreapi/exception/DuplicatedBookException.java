package com.bookstore.bookstoreapi.exception;

public class DuplicatedBookException extends RuntimeException {
    
    public DuplicatedBookException(String message){
        super(message);
    }

    public DuplicatedBookException(String message, Throwable cause){
        super(message, cause);
    }
}
