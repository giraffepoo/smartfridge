package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import com.eightb.smartfridge.model.camera.Predictions;
import com.eightb.smartfridge.repository.FoodItemRepository;
import com.eightb.smartfridge.util.TwilioMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    private FoodItemRepository repository;

    @Override
    public List<FoodItem> getAllFoodItems() {
        return repository.findAll();
    }

    @Override
    public FoodItem getFoodItem(String name) {
        return repository.findByName(name.toLowerCase().trim());
    }

    @Override
    public FoodItem addFoodItem(String name) {
        return addItemBasedOnQuantity(repository.findByName(name.toLowerCase().trim()), name.toLowerCase().trim());

    }

    @Override
    public FoodItem addFoodItem(Predictions predictions) {
        String nameOfItemToAdd = predictions.getLabels().get(0).toLowerCase().trim();

        return addItemBasedOnQuantity(repository.findByName(nameOfItemToAdd), nameOfItemToAdd);
    }

    private FoodItem addItemBasedOnQuantity(FoodItem foodToAdd, String nameOfItemToAdd) {
        int imageId = imageIdMap.getOrDefault(nameOfItemToAdd, 0); //default imageId if not found
        if (foodToAdd == null) {
            return repository.save(new FoodItem(nameOfItemToAdd,1, imageId));
        } else {
            foodToAdd.setQuantity(foodToAdd.getQuantity() + 1);
            return repository.save(foodToAdd);
        }
    }


    @Override
    public FoodItem removeFoodItem(String name) {
        FoodItem foodToAdd = repository.findByName(name.trim().toLowerCase());

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
        return repository.deleteByName(name.toLowerCase().trim());
    }

    @Override
    public void textUserAllFoodItems() {
        List<FoodItem> allFoodItems = repository.findAll();
        TwilioMessage.sendSMSMessage(formatListFoodItemIntoString(allFoodItems));
    }

    @Override
    public List<FoodItem> getAllLowQuantityItems() {
        return repository.findByQuantityLessThanEqual(1);
    }

    @Override
    public void textUserAllLowQuantityFoodItems() {
        List<FoodItem> allFoodItems = repository.findByQuantityLessThanEqual(1);
        TwilioMessage.sendSMSMessage(formatListFoodItemIntoStringWithRestock(allFoodItems));
    }

    public String formatListFoodItemIntoStringWithRestock(List<FoodItem> foodItems) {
        StringBuilder sb = new StringBuilder();
        sb.append("In your fridge, you might be running low on: \n");

        int i = 1;
        for (FoodItem fi : foodItems) {
            sb.append(i).append(". ").append(fi.getName()).append(": ").append("Quantity remaining=").append(fi.getQuantity()).append("\n")
                    .append("Buy more at: ").append("https://www.walmart.ca/search/").append(fi.getName()).append("/N-117").append("\n");
            i++;
        }

        return sb.toString();
    }

    public String formatListFoodItemIntoString(List<FoodItem> foodItems) {
        StringBuilder sb = new StringBuilder();
        sb.append("Current items in your fridge: \n");

        for (FoodItem fi : foodItems) {
            sb.append(fi.getName()).append(": ").append("Quantity remaining = ").append(fi.getQuantity()).append("\n");
        }

        return sb.toString();
    }

}
