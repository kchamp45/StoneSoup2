package com.epicodus.stonesoup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Recipes2Activity extends AppCompatActivity {
    private String[] recipes2 = new String[] {"Chicken Noodle Soup", "Minestrone Soup",
            "Hearty Chick Peas Soup"};
    @Bind(R.id.recipes2TextView) TextView mRecipes2TextView;
    @Bind(R.id.recipes2ListView) ListView mRecipes2ListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes2);
        ButterKnife.bind(this);

        MyRecipesArrayAdapter adapterB = new MyRecipesArrayAdapter(this, android.R.layout.simple_list_item_1, recipes2);
        mRecipes2ListView.setAdapter(adapterB);
    }
}

