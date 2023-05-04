package com.bookstore.bookstoreapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookstoreapi.exception.BookNotFoundException;
import com.bookstore.bookstoreapi.exception.DuplicatedBookException;
import com.bookstore.bookstoreapi.model.Book;
import com.bookstore.bookstoreapi.repository.BookRepository;

@Service
public class BookstoreService {

    @Autowired
    private BookRepository bookrepository;

    public Book addBook(Book book){
        //check if book already exists
        if(!bookrepository.findById(book.getIsbn()).isPresent()){
            return bookrepository.save(book);
        }else{
            throw new DuplicatedBookException("Book with ISBN: " + book.getIsbn() + " already exists");
        }
    }

    public Book updateBook(Book book){
        //check if book exists
        if(bookrepository.findById(book.getIsbn()).isPresent()){
            //find the book by ISBN and populate new values from request
            Book existingBook = bookrepository.findById(book.getIsbn()).get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthorList(book.getAuthorList());
            existingBook.setYear(book.getYear());
            existingBook.setPrice(book.getPrice());
            existingBook.setGenre(book.getGenre());
            return bookrepository.save(existingBook);
        }else{
            throw new BookNotFoundException("Book with ISBN: " + book.getIsbn() + " not found, unable to update");
        }
    }

    public void deleteBookById(String isbn){
        //check if book exists
        if(bookrepository.findById(isbn).isPresent()){
            bookrepository.deleteById(isbn);
        }else{
            throw new BookNotFoundException("Book with ISBN: " + isbn + " not found, unable to delete");
        }
    }

    public List<Book> getBookByTitleOrAuthorName(Map<String, String> bookParams){
        String title = bookParams.get("title") == null ? "" : (String) bookParams.get("title");
        String authorName = bookParams.get("authorName") == null ? "" : (String) bookParams.get("authorName");
        return bookrepository.findByTitleOrAuthorName(title, authorName);
    }

    public List<Book> getBookByTitleAndAuthorName(String title, String name){
        return bookrepository.findByTitleAndAuthorName(title, name);
    }
}
