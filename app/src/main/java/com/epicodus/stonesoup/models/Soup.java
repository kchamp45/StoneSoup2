package com.epicodus.stonesoup.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Soup {
    private String name;
    private int rating;
    private int prepTime;
    private ArrayList<String> ingredients = new ArrayList<>();
    private String imageUrl;
    private String restriction;

    public Soup() {
    }

    public Soup(String name, int rating, int prepTime, ArrayList<String> ingredients, String imageUrl, String restriction) {
        this.name = name;
        this.rating = rating;
        this.prepTime = prepTime;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
        this.restriction = restriction;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getPrepTime() {
        int minutes = prepTime / 60;
        return minutes;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRestriction() {
        return restriction;
    }
}