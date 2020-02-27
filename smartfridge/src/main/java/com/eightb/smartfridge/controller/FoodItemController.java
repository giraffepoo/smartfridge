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

    @GetMapping("")
    List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/text/all-items")
    boolean getAllFoodItemsText() {
        foodItemService.textUserAllFoodItems();
        return true;
    }

    @GetMapping("/text/low-quantity-items")
    boolean getAllLowQuantityItemsText() {
        foodItemService.textUserAllLowQuantityFoodItems();
        return true;
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



    //============= duplicate endpoints (testing purposes - GET not working for us on DE1)
    @PostMapping("")
    List<FoodItem> getAllFoodItemsPOST() {
        return foodItemService.getAllFoodItems();
    }

    @PostMapping("/text/all-items")
    boolean getAllFoodItemsTextPOST() {
        foodItemService.textUserAllFoodItems();
        return true;
    }

    @PostMapping("/text/low-quantity-items")
    boolean getAllLowQuantityItemsTextPOST() {
        foodItemService.textUserAllLowQuantityFoodItems();
        return true;
    }

    @PostMapping("/{name}")
    FoodItem getFoodItemPOST(@PathVariable String name) {
        return foodItemService.getFoodItem(name);
    }

}
