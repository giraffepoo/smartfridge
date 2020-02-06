package com.eightd.smartfridge.controller;

import com.eightd.smartfridge.model.FoodItem;
import com.eightd.smartfridge.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/fooditem")
public class FoodItemController {
    @Autowired
    private FoodItemRepository repository;

    @PostMapping("/")
    public void addItem(String name) {
        repository.save(new FoodItem(name));
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, the time at the server is now " + new Date() + "\n";
    }

}
