package com.JPA.JPA.Repo;

import com.JPA.JPA.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByNameAndCategory(String name, String category);

}
