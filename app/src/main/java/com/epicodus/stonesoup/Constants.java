package com.epicodus.stonesoup;



public class Constants {
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String YUMMLY_KEY_QUERY_PARAMETER = "_app_key";
    public static final String YUMMLY_BASE_URL = "https://api.yummly.com/v1/api/recipes";
    public static final String YUMMLY_ID_QUERY_PARAMETER = "_app_id";
    public static final String YUMMLY_ID_PARAMETER = "44f4ec8c";
    public static final String YUMMLY_INGREDIENT_QUERY_PARAMETER = "allowedIngredient[]";
    public static final String YUMMLY_COURSE_QUERY_PARAMETER = "allowedCourse[]";
    public static final String YUMMLY_COURSE_PARAMETER = "course^course-Soups";
    public static final String YUMMLY_RESTRICTION_PARAMETER ="excludedIngredient[]";
    public static final String YUMMLY_LIMIT_QUERY_PARAMETER = "requirePictures";
    public static final String YUMMLY_LIMIT_PARAMETER = "true";

    public static final String PREFERENCES_RESTRICTION_KEY = "excludedIngredient";
    public static final String FIREBASE_CHILD_SOUPS = "soups";
    public static final String FIREBASE_QUERY_INDEX = "index";

    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_SOUPS = "soups";

    public static final String KEY_SOURCE = "source";
    public static final String SOURCE_SAVED = "saved";
    public static final String SOURCE_FIND = "find";
}
