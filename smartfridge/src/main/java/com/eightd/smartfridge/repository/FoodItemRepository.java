package com.eightd.smartfridge.repository;

import com.eightd.smartfridge.model.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
    public FoodItem findByName(String name);
}
