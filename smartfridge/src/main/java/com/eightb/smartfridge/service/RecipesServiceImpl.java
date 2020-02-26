package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import com.eightb.smartfridge.model.edamam.Recipe;
import com.eightb.smartfridge.model.edamam.RecipeQuery;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RecipesServiceImpl implements RecipesService {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private final FoodItemService foodItemService;

    public RecipesServiceImpl(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    //Generate recipes tailored to items currently in the fridge
    //Generates 3 random items
    @Override
    public void textUserSuggestedRecipes() {
        List<FoodItem> allFoodItems = foodItemService.getAllFoodItems();
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://api.edamam.com/search";

        UriComponentsBuilder builderUri = UriComponentsBuilder.fromUriString(resourceUrl)
                .queryParam("app_id", "02a20196")
                .queryParam("app_key","debf626afaa55521cfaa3619d684a27b");

        //Generate 3 indices for allFoodItems, perform API call on each item based on index
        ThreadLocalRandom.current().ints(0, allFoodItems.size()-1).distinct().limit(3)
                .forEach(i -> {
                    builderUri.replaceQueryParam("q", allFoodItems.get(i).getName()); //set query to food item name at the random index
                    RecipeQuery recipeQuery = restTemplate.getForObject(builderUri.build().toUri(), RecipeQuery.class);

                    assert recipeQuery != null;
                    if(recipeQuery.getCount() > 1) {
                        Recipe currRecipe = recipeQuery.getHits().get(0).getRecipe();
                        TwilioMessage.sendMMSMessge(formatRecipeIntoTextMessage(currRecipe, allFoodItems.get(i).getName()), currRecipe.getImage());
                        System.out.println(formatRecipeIntoTextMessage(currRecipe,  allFoodItems.get(i).getName()));
                    }
                }
        );
    }

    private String formatRecipeIntoTextMessage(Recipe recipe, String fridgeItemName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Using ").append(fridgeItemName).append(" in your fridge:\n");
        sb.append(recipe.getLabel()).append("\n");
        sb.append("Ingredients Required:\n");
        for(String ingredientLine : recipe.getIngredientLines()) {
            sb.append(ingredientLine).append("\n");
        }
        sb.append("Serves up to: ").append(recipe.getYield()).append("\n");
        sb.append("Instructions: ").append(recipe.getUrl());
        return sb.toString();
    }

}
