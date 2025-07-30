package com.example.userDB.service;


import com.example.userDB.model.User;
import com.example.userDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) throws Exception {
        if (!isValidEmail(user.getEmail())) {
            throw new Exception("Invalid email format");
        }

        if (user.getPassword().length() < 6) {
            throw new Exception("Password must be at least 6 characters");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already registered");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }

        user.setPassword(hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
    }

    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

