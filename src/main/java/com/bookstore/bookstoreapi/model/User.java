package com.bookstore.bookstoreapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;

@Document(collection = "users")
public class User {
    
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    
    @Id
    private long id;

    @NotEmpty(message = "User name must not be empty")
    private String username;
    @NotEmpty(message = "Password must not be empty")
    private String password;
    @NotEmpty(message = "A role must be assigned to the user")
    private String role;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
    }
}
