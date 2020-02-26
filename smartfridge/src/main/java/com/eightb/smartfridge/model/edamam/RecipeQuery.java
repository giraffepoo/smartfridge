package com.eightb.smartfridge.model.edamam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeQuery {
    private int count;
    private List<Recipe> hits;

    public RecipeQuery() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Recipe> getHits() {
        return hits;
    }

    public void setHits(List<Recipe> hits) {
        this.hits = hits;
    }
}
