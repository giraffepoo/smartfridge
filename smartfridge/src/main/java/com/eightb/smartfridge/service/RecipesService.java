package com.eightb.smartfridge.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;

@Service
public interface RecipesService {
    void textUserSuggestedRecipes() throws IOException;
}
