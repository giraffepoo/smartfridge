package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipesServiceImpl implements RecipesService {
    private final FoodItemService foodItemService;

    public RecipesServiceImpl(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @Override
    public void textUserPossibleRecipes() {
        List<FoodItem> allFoodItems = foodItemService.getAllFoodItems();


    }

}
