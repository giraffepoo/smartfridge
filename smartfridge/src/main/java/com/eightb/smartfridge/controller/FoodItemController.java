package com.eightb.smartfridge.controller;

import com.eightb.smartfridge.model.FoodItem;
import com.eightb.smartfridge.repository.FoodItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fooditem")
public class FoodItemController {

    private final FoodItemRepository repository;

    public FoodItemController(FoodItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    List<FoodItem> getAllFoodItems() {
        return repository.findAll();
    }

    @GetMapping("/{name}")
    FoodItem getFoodItem(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PostMapping("/add")
    public FoodItem addFoodItem(@RequestBody String name) {
        FoodItem foodToAdd = repository.findByName(name);

        if (foodToAdd == null) {
            return repository.save(new FoodItem(name,1));
        } else {
            foodToAdd.setQuantity(foodToAdd.getQuantity() + 1);
            return repository.save(foodToAdd);
        }
    }

    @PostMapping("/remove")
    public FoodItem removeFoodItem(@RequestBody String name) {
        FoodItem foodToAdd = repository.findByName(name);

        if (foodToAdd != null) {
            if (foodToAdd.getQuantity() <= 1) { //delete since we have no more of that item
                repository.deleteByName(name);
            } else {
                foodToAdd.setQuantity(foodToAdd.getQuantity() - 1);
                return repository.save(foodToAdd);
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    Long deleteFoodItem(@PathVariable String name) {
        return repository.deleteByName(name);
    }
}
