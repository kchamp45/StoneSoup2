package com.epicodus.stonesoup.services;

import android.util.Log;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.models.Soup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RecipeService {
    public static void findRecipes(String soup, String allergy, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YUMMLY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YUMMLY_INGREDIENT_QUERY_PARAMETER, soup)
                .addQueryParameter(Constants.YUMMLY_RESTRICTION_PARAMETER, allergy)
                .addQueryParameter(Constants.YUMMLY_ID_QUERY_PARAMETER, Constants.YUMMLY_ID_PARAMETER)
                .addQueryParameter(Constants.YUMMLY_COURSE_QUERY_PARAMETER, Constants.YUMMLY_COURSE_PARAMETER)
                .addQueryParameter(Constants.YUMMLY_LIMIT_QUERY_PARAMETER, Constants.YUMMLY_LIMIT_PARAMETER)
                .addQueryParameter(Constants.YUMMLY_KEY_QUERY_PARAMETER, Constants.API_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<Soup> processResults(Response response) {
        ArrayList<Soup> soups = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject yummlyJSON = new JSONObject(jsonData);
            JSONObject criteriaJSON = yummlyJSON.getJSONObject("criteria");
            String restriction = criteriaJSON.getJSONArray("excludedIngredient").get(0).toString();
            JSONArray matchJSON = yummlyJSON.getJSONArray("matches");
            for (int i = 0; i < matchJSON.length(); i++) {
                JSONObject soupJSON = matchJSON.getJSONObject(i);
                String name = soupJSON.getString("recipeName");
                int rating = soupJSON.getInt("rating");
                int totalPrepTime = soupJSON.getInt("totalTimeInSeconds");

                String oldImageUrl = soupJSON.getJSONArray("smallImageUrls").get(0).toString();
                String imageUrl = oldImageUrl.replace("=s9", "");
                Log.v("soup", imageUrl);
                ArrayList<String> ingredients = new ArrayList<>();
                JSONArray ingredientJSON = soupJSON.getJSONArray("ingredients");
                for (int y = 0; y < ingredientJSON.length(); y++) {
                    ingredients.add(ingredientJSON.get(y).toString());
                }
                Soup soup = new Soup(name, rating, totalPrepTime, ingredients, imageUrl, restriction);
                soups.add(soup);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return soups;
    }

}
