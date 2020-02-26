package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RecipesServiceImpl implements RecipesService {
    private final FoodItemService foodItemService;

    public RecipesServiceImpl(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    //Generate recipes tailored to items currently in the fridge
    @Override
    public void textUserSuggestedRecipes() throws IOException {
        URL url = new URL("https://api.edamam.com/search");
        List<FoodItem> allFoodItems = foodItemService.getAllFoodItems();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("app_id", "02a20196");
        parameters.put("app_key", "debf626afaa55521cfaa3619d684a27b");

        //Generate 5 indices for allFoodItems, perform API call on each item based on index
        ThreadLocalRandom.current().ints(0, allFoodItems.size()-1).distinct().limit(3)
                .forEach(i -> {

                }
        );
    }

}
