package com.example.item_services.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.item_services.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {
}
