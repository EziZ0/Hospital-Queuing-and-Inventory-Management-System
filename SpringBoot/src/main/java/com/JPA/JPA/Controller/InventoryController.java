package com.JPA.JPA.Controller;

import com.JPA.JPA.Model.Inventory;
import com.JPA.JPA.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/get")
    public ResponseEntity<Inventory> getInventoryByCategory(@RequestBody Map<String, String> requestBody) {
        String category = requestBody.get("category");
        if (category == null || category.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Optional<Inventory> optionalInventory = inventoryService.getByCategory(category);
        if (optionalInventory.isPresent()) {
            return ResponseEntity.ok(optionalInventory.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/updateInventory")
    public ResponseEntity<String> updateInventoryCount(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        String category = requestBody.get("category");
        int countToReduce = Integer.parseInt(requestBody.get("count"));

        if (name == null || name.isEmpty() || category == null || category.isEmpty() || countToReduce <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
        }

        // Fetch inventory by name and category
        Optional<Inventory> optionalInventory = inventoryService.getByNameAndCategory(name, category);
        if (!optionalInventory.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory item not found for name: " + name + " and category: " + category);
        }

        Inventory inventory = optionalInventory.get();

        // Update count
        int updatedCount = inventory.getCount() - countToReduce;
        if (updatedCount < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient inventory to reduce by count: " + countToReduce);
        }

        // Set updated count and save the entity
        inventory.setCount(updatedCount);
        inventoryService.save(inventory);

        // Check if the updated count is less than the eoq
        if (updatedCount < inventory.getEoq()) {
            return ResponseEntity.ok("order");
        } else {
            return ResponseEntity.ok("no need");
        }
    }
}
