package com.JPA.JPA.Repo;

import com.JPA.JPA.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailAndPassword(String email, String password); // Find by email and password
    Optional<Users> findByEmail(String email); // Find by email
    Optional<Users> findByNumber(Long Number); // Find by phone number (renamed for clarity)
}
