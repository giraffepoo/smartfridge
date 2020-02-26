package com.eightb.smartfridge.controller;

import com.eightb.smartfridge.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipesController {
    @Autowired
    RecipesService recipesService;

    @GetMapping("/text/suggest-recipes")
    void textSuggestedRecipes() {
        recipesService.textUserSuggestedRecipes();
    }

}