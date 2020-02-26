package com.eightb.smartfridge.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest
public class RecipesServiceTest {
    @Autowired
    RecipesService recipesService;

    @Test
    void TestRandomRecipes() throws IOException, URISyntaxException {
        recipesService.textUserSuggestedRecipes();
    }
}
