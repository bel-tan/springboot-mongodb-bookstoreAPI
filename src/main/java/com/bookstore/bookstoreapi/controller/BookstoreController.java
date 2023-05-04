package com.bookstore.bookstoreapi.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.service.BookstoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bookstore")
public class BookstoreController {
    
    @Autowired
    private BookstoreService service;

    @PostMapping("/addBook")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody @Valid Book book){
        return service.addBook(book);
    }

    @PutMapping("/updateBook")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@RequestBody @Valid Book book){
        return service.updateBook(book);
    }

    @PostMapping("/deleteBookById/{isbn}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable String isbn){
        service.deleteBookById(isbn);
    }

    @GetMapping("/findByTitleOrAuthorName")
    public List<Book> getBookByTitleOrAuthorName(@RequestParam Map<String,String> bookParams){
        return service.getBookByTitleOrAuthorName(bookParams);
    }

    //REST-style URLs always need the full URL path, so {title} and {name} cannot be null or empty
    @GetMapping("/findByTitleAndAuthorName/{title}/{name}")
    public List<Book> getBookByTitleAndAuthorName(@PathVariable String title, @PathVariable String name){
        return service.getBookByTitleAndAuthorName(title, name);
    }

    @GetMapping("/Welcome")
    public String welcome(){
        return "Welcome!";
    }

}
