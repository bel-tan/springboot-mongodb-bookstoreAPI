package com.bookstore.bookstoreapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.bookstoreapi.exception.DuplicatedUserException;
import com.bookstore.bookstoreapi.model.User;
import com.bookstore.bookstoreapi.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public User addUser(User user){
        if(!repository.findByUsername(user.getUsername()).isPresent()){
            user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME)); //auto generate a user id for this record
            user.setPassword(passwordEncoder.encode(user.getPassword())); //encode the password before storing in DB
            return repository.save(user);
        }else{
            throw new DuplicatedUserException("Username: " + user.getUsername() + " already exists");
        }
    }
}
