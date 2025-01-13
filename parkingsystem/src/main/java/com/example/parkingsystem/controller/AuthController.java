package com.example.parkingsystem.controller;

import com.example.parkingsystem.model.User;
import com.example.parkingsystem.service.UserService;
import com.example.parkingsystem.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            if (userService.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
            }

            // Save the new user
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Signup failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            // Find the user by email
            User foundUser = userService.findByEmail(user.getEmail());
            if (foundUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Validate the password
            if (!foundUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

            // Backend Login
            String role = "role" + foundUser.getRole().name(); // Ensure role has "ROLE_" prefix
            String token = jwtUtil.generateToken(foundUser.getEmail(), role);

            // Sending both token and role in the response
            return ResponseEntity.ok("{\"token\":\"" + token + "\", \"role\":\"" + role + "\"}");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
        }
    }
}
