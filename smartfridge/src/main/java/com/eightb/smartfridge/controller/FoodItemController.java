package com.eightb.smartfridge.controller;

import com.eightb.smartfridge.model.FoodItem;
import com.eightb.smartfridge.repository.FoodItemRepository;
import com.eightb.smartfridge.service.FoodItemService;
import com.eightb.smartfridge.service.TwilioMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fooditem")
public class FoodItemController {
    @Autowired
    FoodItemService foodItemService;

    @GetMapping("/")
    List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/text/all-items")
    void getAllFoodItemsText() {
        foodItemService.textUserAllFoodItems();
    }

    @GetMapping("/text/low-quantity-items")
    void getAllLowQuantityItemsText() {
        foodItemService.textUserAllLowQuantityFoodItems();
    }

    @GetMapping("/{name}")
    FoodItem getFoodItem(@PathVariable String name) {
        return foodItemService.getFoodItem(name);
    }

    @PostMapping("/add")
    FoodItem addFoodItem(@RequestBody String name) {
       return foodItemService.addFoodItem(name);
    }

    @PostMapping("/remove")
    public FoodItem removeFoodItem(@RequestBody String name) {
        return foodItemService.removeFoodItem(name);
    }

    @DeleteMapping("/{name}")
    Long deleteFoodItem(@PathVariable String name) {
        return foodItemService.deleteFoodItem(name);
    }
}
