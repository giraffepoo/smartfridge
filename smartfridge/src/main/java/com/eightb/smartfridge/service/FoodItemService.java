package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import com.eightb.smartfridge.model.camera.Predictions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@Service
public interface FoodItemService {
    Map<String, Integer> imageIdMap = Map.ofEntries(
            entry("default", 0),
            entry("watermelon", 1),
            entry("banana", 2),
            entry("cheese", 3),
            entry("cucumber", 4),
            entry("milk", 5),
            entry("lemon", 6),
            entry("tomato", 7),
            entry("sandwich", 8),
            entry("wine", 9),
            entry("strawberry", 10),
            entry("pizza", 11),
            entry("orange", 12),
            entry("hot dog", 13),
            entry("donut", 14),
            entry("carrot", 15),
            entry("cake", 16),
            entry("burger", 17),
            entry("broccoli", 18),
            entry("avocado", 19),
            entry("bacon", 20),
            entry("egg", 21),
            entry("lettuce", 22),
            entry("raw beef", 23),
            entry("raw fish", 24),
            entry("sushi", 25),
            entry("bread", 26),
            entry("pop", 27),
            entry("pepper", 28),
            entry("peach", 29),
            entry("prune", 30),
            entry("jam", 31),
            entry("grapes", 32),
            entry("eggplant", 33),
            entry("butter", 34),
            entry("potato", 35),
            entry("mango", 36),
            entry("apricot", 37),
            entry("cherry", 38),
            entry("kiwi", 39),
            entry("yogurt", 40),
            entry("apple", 41)
    );

    //FoodItem Database Operations
    List<FoodItem> getAllFoodItems();
    FoodItem getFoodItem(String name);
    FoodItem addFoodItem(String name);
    FoodItem addFoodItem(Predictions predictions);
    FoodItem removeFoodItem(String name);
    Long deleteFoodItem(String name);
    List<FoodItem> getAllLowQuantityItems();

    //Twilio Texting Operations
    void textUserAllFoodItems();
    void textUserAllLowQuantityFoodItems();

    //Formatting list of food items to String
    String formatListFoodItemIntoStringWithRestock(List<FoodItem> foodItems);
    String formatListFoodItemIntoString(List<FoodItem> foodItems);

}
