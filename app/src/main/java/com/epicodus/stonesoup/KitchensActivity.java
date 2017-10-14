package com.epicodus.stonesoup;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class KitchensActivity extends AppCompatActivity {
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] kitchens = new String[] {"Kim's Kitchen", "Rich's Farm",
             "Only Friends Space"};

    private String[] kitLocations = new String[]{"12345 SW Downhill Lane",
            "Somewhere in Gresham", "23456 Someone's Basement"};

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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Intent firstKitchen = new Intent(KitchensActivity.this, Recipes1Activity.class);
                    startActivity(firstKitchen);
                }else if(position == 1){
                    Intent secondKitchen = new Intent(KitchensActivity.this, Recipes2Activity.class);
                    startActivity(secondKitchen);
                }else if(position == 2) {
                    Intent thirdKitchen = new Intent(KitchensActivity.this, Recipes3Activity.class);
                    startActivity(thirdKitchen);
                }
            }
        });
    }
}