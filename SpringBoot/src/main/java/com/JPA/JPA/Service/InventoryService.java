package com.JPA.JPA.Service;

import com.JPA.JPA.Model.Inventory;
import com.JPA.JPA.Repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepo inventoryRepo;

    // This method finds the first inventory item by category only
    public Optional<Inventory> getByCategory(String category) {
        Optional<Inventory> inventoryList = inventoryRepo.findByNameAndCategory(category,category);
        return inventoryList.isEmpty() ? Optional.empty() : Optional.of(inventoryList.get());
    }

    // This method finds the inventory item by both name and category
    public Optional<Inventory> getByNameAndCategory(String name, String category) {
        return inventoryRepo.findByNameAndCategory(name, category);
    }

    // Save or update inventory item
    public void save(Inventory inventory) {
        inventoryRepo.save(inventory);
    }
}
