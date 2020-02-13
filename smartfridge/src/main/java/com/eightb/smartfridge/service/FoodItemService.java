package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;

import java.util.List;

public interface FoodItemService {
    List<FoodItem> getAllFoodItems();
    FoodItem getFoodItem(String name);
    FoodItem addFoodItem(String name);
    FoodItem removeFoodItem(String name);
    Long deleteFoodItem(String name);
    List<FoodItem> getAllLowQuantityItems();

    void textUserAllFoodItems();
    void textUserAllLowQuantityFoodItems();

}
