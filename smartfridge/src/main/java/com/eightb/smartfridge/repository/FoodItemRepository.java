package com.eightb.smartfridge.repository;

import com.eightb.smartfridge.model.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
    public FoodItem findByName(String name);
    public Long deleteByName(String name);
}
