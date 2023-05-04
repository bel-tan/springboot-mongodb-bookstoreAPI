package com.bookstore.bookstoreapi.model;

import java.time.LocalDate;

import com.bookstore.bookstoreapi.validation.CustomDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

public class Author {

    @NotEmpty(message = "Please provide author's name")
    private String name;
    
    //MongoDB does not allow a NULL date.
    //Default value 1900-01-01 is used to represent empty/NULL dob
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Past(message = "Please provide a past date")
    private LocalDate dob;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    
}
