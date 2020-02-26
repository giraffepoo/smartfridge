package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RecipesServiceImpl implements RecipesService {
    private final FoodItemService foodItemService;

    public RecipesServiceImpl(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    //Generate recipes tailored to items currently in the fridge
    @Override
    public void textUserSuggestedRecipes() {
        List<FoodItem> allFoodItems = foodItemService.getAllFoodItems();

        //Generate 5 indices for allFoodItems, perform API call on each item based on index
        ThreadLocalRandom.current().ints(0, allFoodItems.size()-1).distinct().limit(5).forEach(System.out::println);
    }

}
