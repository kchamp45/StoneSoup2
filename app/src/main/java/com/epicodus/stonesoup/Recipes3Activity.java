package com.epicodus.stonesoup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Recipes3Activity extends AppCompatActivity {
    private String[] recipes3 = new String[] {"Beef Potatoe Stew", "New England Clam Chowder",
            "Seafood Gumbo"};
    @Bind(R.id.recipes3TextView) TextView mRecipes3TextView;
    @Bind(R.id.recipes3ListView) ListView mRecipes3ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes3);
        ButterKnife.bind(this);

        MyRecipesArrayAdapter adapterC = new MyRecipesArrayAdapter(this, android.R.layout.simple_list_item_1, recipes3);
        mRecipes3ListView.setAdapter(adapterC);
    }
}
