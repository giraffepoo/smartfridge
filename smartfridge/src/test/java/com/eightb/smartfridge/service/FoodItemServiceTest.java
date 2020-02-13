package com.eightb.smartfridge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FoodItemServiceTest {
    @Autowired
    FoodItemService foodItemService;

    @Test
    void foodServiceTextAll() {
        foodItemService.textUserAllFoodItems();
    }
}
