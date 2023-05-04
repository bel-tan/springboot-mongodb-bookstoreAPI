package com.bookstore.bookstoreapi.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Document(collection = "books")
public class Book {
    
    @Id
    @NotEmpty(message = "Please provide book ISBN")
    private String isbn;

    @NotEmpty(message = "Please provide book title")
    private String title;

    @JsonProperty("author")
    @Valid
    private List<Author> authorList;

    @Min(value=1)
    private int year;

    @Min(value=1)
    private double price;
    private String genre;
    
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Author> getAuthorList() {
        return authorList;
    }
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Override
    public String toString() {
        return "Book [isbn=" + isbn + ", title=" + title + ", author=" + authorList + ", year=" + year + ", price=" + price
                + ", genre=" + genre + "]";
    }
}
