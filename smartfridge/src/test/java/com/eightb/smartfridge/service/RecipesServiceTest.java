package com.eightb.smartfridge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecipesServiceTest {
    @Autowired
    RecipesService recipesService;

    @Test
    void TestRandomRecipes() {
        recipesService.textUserSuggestedRecipes();
    }
}
