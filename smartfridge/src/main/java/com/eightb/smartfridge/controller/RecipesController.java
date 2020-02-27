package com.eightb.smartfridge.controller;

import com.eightb.smartfridge.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/recipes")
public class RecipesController {
    @Autowired
    RecipesService recipesService;

    @GetMapping("/text/suggest-recipes")
    boolean textSuggestedRecipes() throws IOException, URISyntaxException {
        recipesService.textUserSuggestedRecipes();
        return true;
    }

    //============= duplicate endpoints (testing purposes - GET not working for us on DE1)
    @PostMapping("/text/suggest-recipes")
    boolean textSuggestedRecipesPOST() throws IOException, URISyntaxException {
        recipesService.textUserSuggestedRecipes();
        return true;
    }

}
