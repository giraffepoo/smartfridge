package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import com.eightb.smartfridge.model.edamam.Recipe;
import com.eightb.smartfridge.model.edamam.RecipeQuery;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
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
    @Override
    public void textUserSuggestedRecipes() {
        List<FoodItem> allFoodItems = foodItemService.getAllFoodItems();
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://api.edamam.com/search";

        UriComponentsBuilder builderUri = UriComponentsBuilder.fromUriString(resourceUrl)
                .queryParam("app_id", "02a20196")
                .queryParam("app_key","debf626afaa55521cfaa3619d684a27b");


        //Generate 3 indices for allFoodItems, perform API call on each item based on index
        ThreadLocalRandom.current().ints(0, allFoodItems.size()-1).distinct().limit(3)
                .forEach(i -> {
                    builderUri.replaceQueryParam("q", allFoodItems.get(i)); //set query to food item name at the random index
                    RecipeQuery recipeQuery = restTemplate.getForObject(resourceUrl, RecipeQuery.class, builderUri);

                    assert recipeQuery != null;
                    if(recipeQuery.getCount() > 1) {
                        Recipe currRecipe = recipeQuery.getHits().get(0);
                        TwilioMessage.sendMMSMessge(formatRecipeIntoTextMessage(currRecipe, allFoodItems.get(i).getName()), currRecipe.getImageUrl());
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
        sb.append(recipe.getIngredientLines()).append("\n");
        sb.append("Instructions: ").append(recipe.getUrl());
        return sb.toString();
    }

}
