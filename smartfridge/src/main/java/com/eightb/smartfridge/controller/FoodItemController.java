package com.eightb.smartfridge.controller;

import com.eightb.smartfridge.model.FoodItem;
import com.eightb.smartfridge.model.camera.Predictions;
import com.eightb.smartfridge.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/camera/add")
    FoodItem addFoodItem(@RequestBody Predictions predictions) {
        return foodItemService.addFoodItem(predictions);
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
