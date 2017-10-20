package com.epicodus.stonesoup;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.stonesoup.services.RecipeService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.epicodus.stonesoup.R.drawable.soup;


public class SoupsActivity extends AppCompatActivity {
    public static final String TAG = SoupsActivity.class.getSimpleName();

    @Bind(R.id.soupTextView) TextView mSoupTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Soup> soups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soups);
        ButterKnife.bind(this);

        Typeface fancyFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mSoupTextView.setTypeface(fancyFont);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mSoupTextView.setText("Try these soup: " + soup);
        getSoups(soup);
    }

    private void getSoups(String soup) {
        final RecipeService recipeService = new RecipeService();
        recipeService.findRecipes(soup, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                soups = recipeService.processResults(response);

                SoupsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       String[] soupNames = new String[soups.size()];
                        for (int i = 0; i < soupNames.length; i++) {
                            soupNames[i] = soups.get(i).getName();
                        }
                        MySoupArrayAdapter adapter = new MySoupArrayAdapter(SoupsActivity.this,
                                android.R.layout.simple_list_item_1, soupNames);
                        mListView.setAdapter(adapter);

                        for (Soup soup : soups) {
                            Log.d(TAG, "Name: " + soup.getName());
                            Log.d(TAG, "Rating: " + soup.getRating());
                            Log.d(TAG, "Image url: " + soup.getImageUrl());
                            Log.d(TAG, "Ingredients: " + android.text.TextUtils.join(", ", soup.getIngredients()));

                        }
                    }
                });
            }
        });
    }
}


////                    Intent firstKitchen = new Intent(SoupsActivity.this, Recipes1Activity.class);
////                    startActivity(firstKitchen);
////                }else if(position == 1){
////                    Intent secondKitchen = new Intent(SoupsActivity.this, Recipes2Activity.class);
////                    startActivity(secondKitchen);
////                }else if(position == 2) {
////                    Intent thirdKitchen = new Intent(SoupsActivity.this, Recipes3Activity.class);
////                    startActivity(thirdKitchen);
////                }
//
//        Intent intent = getIntent();
//        String location = intent.getStringExtra("soup");
//        mSoupTextView.setText("Here are the soups: " + soup);