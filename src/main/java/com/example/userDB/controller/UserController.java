package com.example.userDB.controller;


import com.example.userDB.model.User;
import com.example.userDB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok().body(
                    new Response(savedUser.getId(), "User registered successfully")
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(null, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    record Response(Long userId, String message) {}
}
