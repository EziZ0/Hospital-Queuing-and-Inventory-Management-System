package com.JPA.JPA.Controller;

import com.JPA.JPA.Model.Admin;
import com.JPA.JPA.Model.Hospital;
import com.JPA.JPA.Model.Users;
import com.JPA.JPA.Repo.AdminRepo;
import com.JPA.JPA.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//demo
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    private AdminRepo adminRepo;

    @CrossOrigin(origins = "*")
    @PostMapping("addHospital")
    public boolean add(@RequestBody Admin admin){
           try {
            // Check if the email already exists
            if (adminRepo.findByEmail(admin.getEmail()).isPresent()) {
                return false; // Email already exists
            }

            // Check if the phone number already exists
            if (adminRepo.findByNumber(admin.getNumber()).isPresent()) {
                return false; // Phone number already exists
            }

            // Save the user if both checks pass
            adminRepo.save(admin);
            return true; // Registration successful
        } catch (Exception e) {
            return false; // An error occurred
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("admin/login")
    public boolean newone(@RequestBody Admin admin){
        Optional<Admin> user = adminRepo.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
        return user.isPresent();
    }


    @GetMapping("api/admin")
    public ResponseEntity<?> getHospitalByEmail(@RequestParam String email) {
        // Logic to fetch hospital by email
        Optional<Object> hospital = adminRepo.findByEmail(email);

        if (hospital != null) {
            return ResponseEntity.ok(hospital);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Hospital not found with email: " + email);
        }
    }

}


