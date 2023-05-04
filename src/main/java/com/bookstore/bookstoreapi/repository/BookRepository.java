package com.bookstore.bookstoreapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.bookstore.bookstoreapi.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query(value = "{$or :[{'title': ?0},{'authorList.name': ?1}]}")
    List<Book> findByTitleOrAuthorName(String title, String authorName);

    @Query(value = "{'title': ?0, 'authorList.name': ?1}")
    List<Book> findByTitleAndAuthorName(String title, String authorName);
    
}