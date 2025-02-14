package com.example.springapi.service;

import com.example.springapi.model.User;
import com.example.springapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implements loadUserByUsername method
 * This method is call to authenticate the user
 * userRepository is used to find the user in the database
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Load the User
     *
     * @param username to find in the database
     * @return a User
     * @throws UsernameNotFoundException if username not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // try to find the user
        User user = userRepository.findByUsername(username);

        // check if user
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
