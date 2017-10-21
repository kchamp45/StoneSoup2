package com.epicodus.stonesoup.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.epicodus.stonesoup.R;
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


public class SoupListActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private SoupListAdapter mAdapter;

    public ArrayList<Soup> soups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soups);
        ButterKnife.bind(this);

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
            public void onResponse(Call call, Response response) throws IOException {
                soups = recipeService.processResults(response);

                SoupListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new SoupListAdapter(getApplicationContext(), soups);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(SoupListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for(Soup soup : soups){
                            Log.d("SoupListActivity", soup.getImageUrl());
                        }

                    }
                });
            }

        });
    }

}

