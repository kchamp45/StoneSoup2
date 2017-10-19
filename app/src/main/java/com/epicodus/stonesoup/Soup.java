package com.epicodus.stonesoup;

import java.util.ArrayList;


public class Soup {
        private String name;
        private int rating;
        private String imageUrl;
        private ArrayList<String> ingredients = new ArrayList<>();

    public Soup(String name, int rating, String imageUrl, ArrayList<String> ingredients) {
        this.name = name;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }
}
