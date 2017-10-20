package com.epicodus.stonesoup.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.adapters.MySoupArrayAdapter;
import com.epicodus.stonesoup.adapters.SoupListAdapter;
import com.epicodus.stonesoup.models.Soup;
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
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private SoupListAdapter mAdapter;

    public ArrayList<Soup> soups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soups);
        ButterKnife.bind(this);

//        Typeface fancyFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
//        mCuisineTextView.setTypeface(fancyFont);

        Intent intent = getIntent();
        String soup = intent.getStringExtra("soup");

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
                        mAdapter = new SoupListAdapter(getApplicationContext(), soups);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(SoupsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);


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