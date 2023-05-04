package com.bookstore.bookstoreapi.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bookstore.bookstoreapi.exception.UserAuthenticationException;
import com.bookstore.bookstoreapi.model.User;
import com.bookstore.bookstoreapi.repository.UserRepository;

@Component
public class UserSecurityDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //this method is used when authenticating the token. If username not found, error will be thrown
        if(repository.findByUsername(username).isPresent()){
            Optional <User> user = repository.findByUsername(username);
            return user.map(UserSecurityDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found " + username));
        }else{
            throw new UserAuthenticationException("Wrong username or password");
        }
    }
}
