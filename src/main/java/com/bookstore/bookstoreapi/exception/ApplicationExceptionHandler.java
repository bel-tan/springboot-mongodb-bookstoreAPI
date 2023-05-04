package com.bookstore.bookstoreapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    
    //this is to handle the required fields and invalid data format
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    //this is to handle invalid data type (e.g: expected int but user keyed in alphabets)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object>  handleTypeConversionError(HttpMessageNotReadableException httpMessageNotReadableException){
        AppException appException = new AppException(
            "Invalid data type provided: " + httpMessageNotReadableException.getMessage(), 
            HttpStatus.BAD_REQUEST,
            "FMT001");
        return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
    }

    //this is to handle the adding of existing book error based on ISBN
    @ExceptionHandler(value = {DuplicatedBookException.class})
    public ResponseEntity<Object> handleDuplicatedBookException(DuplicatedBookException duplicatedBookException){
        AppException appException = new AppException(
            duplicatedBookException.getMessage(), 
            HttpStatus.BAD_REQUEST,
            "BKEX001");

        return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
    }

    //this is to handle the updating and deleting of non-existence book based on ISBN
    @ExceptionHandler(value = {BookNotFoundException.class})
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException bookNotFoundException){
        AppException appException = new AppException(
            bookNotFoundException.getMessage(), 
            HttpStatus.NOT_FOUND,
            "BKEX002");

        return new ResponseEntity<>(appException, HttpStatus.NOT_FOUND);
    }

    //this is to handle invalid date format keyed in by user
    @ExceptionHandler(value = {DateFormatException.class})
    public ResponseEntity<Object> handleDateTimeParseException(DateFormatException dateFormatException){
        AppException appException = new AppException(
            dateFormatException.getMessage(), 
            HttpStatus.BAD_REQUEST,
            "BKEX003");

        return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DuplicatedUserException.class})
    public ResponseEntity<Object> handleDuplicatedUserException(DuplicatedUserException duplicatedUserException){
        AppException appException = new AppException(
            duplicatedUserException.getMessage(), 
            HttpStatus.BAD_REQUEST,
            "USREX001");

        return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
    }

    //this is to handle the adding of existing user error based on username
    @ExceptionHandler(value = {UserAuthenticationException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(UserAuthenticationException userAuthenticationException){
        AppException appException = new AppException(
            userAuthenticationException.getMessage(), 
            HttpStatus.BAD_REQUEST,
            "USREX002");

        return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
    }

    //this is to handle the expiration of token
    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex){
        AppException appException = new AppException(
            "Token expired. Please authenticate again.", 
            HttpStatus.UNAUTHORIZED,
            "USREX003");

        return new ResponseEntity<>(appException, HttpStatus.BAD_REQUEST);
    }

    
}
