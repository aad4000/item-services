package com.example.item_services.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.item_services.Repository.ItemRepository;
import com.example.item_services.model.Item;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // POST: Create a new item
    @PostMapping
    public ResponseEntity<?> createItem(@Valid @RequestBody Item item) {
        try {
            Item savedItem = itemRepository.save(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the item: " + e.getMessage());
        }
    }

    // GET: Retrieve an item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item with ID " + id + " not found.");
        }
    }

    // GET: Retrieve all items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return ResponseEntity.ok(items);
    }

    // PUT: Update an existing item by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable String id, @Valid @RequestBody Item updatedItem) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            Item existingItem = itemOptional.get();
            existingItem.setName(updatedItem.getName());
            existingItem.setDescription(updatedItem.getDescription());
            itemRepository.save(existingItem);
            return ResponseEntity.ok(existingItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item with ID " + id + " not found.");
        }
    }

    // DELETE: Delete an item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable String id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item with ID " + id + " not found.");
        }
    }
}
