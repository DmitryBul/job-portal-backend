package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

   @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        System.out.println(email);
        String password = loginRequest.get("password");
        System.out.println(password);
        
        User user = userService.authenticateUser(email, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}