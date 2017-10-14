package com.epicodus.stonesoup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Recipes1Activity extends AppCompatActivity {
    private String[] recipes1 = new String[] {"Mi Do Bien Soup", "Pho Soup",
            "Bun Bo Hue Soup"};
    @Bind(R.id.recipes1TextView) TextView mRecipes1TextView;
    @Bind(R.id.recipes1ListView) ListView mRecipes1ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes1);
        ButterKnife.bind(this);

        MyRecipesArrayAdapter adapterA = new MyRecipesArrayAdapter(this, android.R.layout.simple_list_item_1, recipes1);
        mRecipes1ListView.setAdapter(adapterA);
    }
}
