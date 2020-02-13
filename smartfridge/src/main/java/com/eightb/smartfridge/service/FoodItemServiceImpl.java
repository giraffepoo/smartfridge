package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import com.eightb.smartfridge.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    private FoodItemRepository repository;
//    private final FoodItemRepository repository;
//
//    public FoodItemServiceImpl(FoodItemRepository repository) {
//        this.repository = repository;
//    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        return repository.findAll();
    }

    @Override
    public FoodItem getFoodItem(String name) {
        return repository.findByName(name);
    }

    @Override
    public FoodItem addFoodItem(String name) {
        FoodItem foodToAdd = repository.findByName(name);

        if (foodToAdd == null) {
            return repository.save(new FoodItem(name,1));
        } else {
            foodToAdd.setQuantity(foodToAdd.getQuantity() + 1);
            return repository.save(foodToAdd);
        }
    }

    @Override
    public FoodItem removeFoodItem(String name) {
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

    @Override
    public Long deleteFoodItem(String name) {
        return repository.deleteByName(name);
    }

    @Override
    public void textUserAllFoodItems() {
        List<FoodItem> allFoodItems = repository.findAll();
        StringBuilder sb = new StringBuilder();

        sb.append("Current items in your fridge: \n");

        for (FoodItem fi : allFoodItems) {
            sb.append(fi.getName()).append(": ").append("qty=").append(fi.getQuantity()).append("\n");
        }

        TwilioMessage.sendMessage(sb.toString());
    }
}
