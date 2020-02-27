package com.eightb.smartfridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AppScheduler {
    @Autowired
    FoodItemService foodItemService;

    @Scheduled(fixedRate = 86400000) //update user every day (86400000ms)
    public void lowFoodItemQuantityScheduler() {
        foodItemService.textUserAllLowQuantityFoodItems();
    }

}
