package com.epicodus.stonesoup;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class KitchensActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] kitchens = new String[] {"Kim's Kitchen", "Rich's Farm",
            "My Favorite Church", "Only Friends Space"};

    private String[] kitLocations = new String[]{"12345 SW Hills Road",
            "Somewhere in Gresham", "67890 NW Worship Lane", "23456 Someone's Basement Blvd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchens);
        ButterKnife.bind(this);

        Typeface fancyFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mLocationTextView.setTypeface(fancyFont);

        MyKitchensArrayAdapter adapter = new MyKitchensArrayAdapter(this, android.R.layout.simple_list_item_1, kitchens, kitLocations);
        mListView.setAdapter(adapter);


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are the kitchens near: " + location);
    }
}
