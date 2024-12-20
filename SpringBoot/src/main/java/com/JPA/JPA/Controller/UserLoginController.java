package com.JPA.JPA.Controller;


import com.JPA.JPA.Model.Users;
import com.JPA.JPA.Repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserLoginController {

    @Autowired
    private UserRepo registerRepo;

    @CrossOrigin(origins = "*")
    @PostMapping("login")
    public boolean add(@RequestBody Users login) {
        Optional<Users> user = registerRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());
        return user.isPresent();
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public boolean register(@RequestBody Users register) {
        try {
            // Check if the email already exists
            if (registerRepo.findByEmail(register.getEmail()).isPresent()) {
                return false; // Email already exists
            }

            // Check if the phone number already exists
            if (registerRepo.findByNumber(register.getNumber()).isPresent()) {
                return false; // Phone number already exists
            }

            // Save the user if both checks pass
            registerRepo.save(register);
            return true; // Registration successful
        } catch (Exception e) {
            return false; // An error occurred
        }
    }





    @GetMapping("/api/users")
    public ResponseEntity<Users> getUserByEmail(@RequestParam String email) {
        Optional<Users> user = registerRepo.findByEmail(email);
        if (user.isPresent()) {
            System.out.println("User found: " + user.get());
        } else {
            System.out.println("User not found with email: " + email);
        }
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



}
