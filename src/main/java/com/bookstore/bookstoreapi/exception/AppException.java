package com.bookstore.bookstoreapi.exception;

import org.springframework.http.HttpStatus;

public class AppException {

    private final String message;
    private final HttpStatus httpStatus;
    private final String errorCode;
    
    public AppException(String message, HttpStatus httpStatus, String errorCode) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
