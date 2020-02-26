package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;
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
    public void textUserSuggestedRecipes() throws IOException, URISyntaxException {
        List<FoodItem> allFoodItems = foodItemService.getAllFoodItems();
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("api.edamam.com").setPath("/search")
                .setParameter("app_id", "02a20196")
                .setParameter("app_key", "debf626afaa55521cfaa3619d684a27b");



        //Generate 3 indices for allFoodItems, perform API call on each item based on index
        ThreadLocalRandom.current().ints(0, allFoodItems.size()-1).distinct().limit(3)
                .forEach(i -> {
                    builder.setParameter("q", allFoodItems.get(i).getName());
                    URI uri = null;
                    try {
                        uri = builder.build();
                        HttpGet httpget = new HttpGet(uri);
                        CloseableHttpResponse response = httpClient.execute(httpget);
                        HttpEntity entity = response.getEntity();
                        if(entity != null) {
                            entity.
                        }
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

}
