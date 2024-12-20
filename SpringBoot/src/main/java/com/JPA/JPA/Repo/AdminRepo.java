package com.JPA.JPA.Repo;

import com.JPA.JPA.Model.Admin;
import com.JPA.JPA.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmailAndPassword(String email, String password);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByNumber(Long number);
}
