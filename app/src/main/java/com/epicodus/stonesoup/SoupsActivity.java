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


public class SoupsActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = SoupsActivity.class.getSimpleName();

    @Bind(R.id.soupTextView) TextView mSoupTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Soup> soups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchens);
        ButterKnife.bind(this);
        getSoups(soup);

        Typeface fancyFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mSoupTextView.setTypeface(fancyFont);

        MySoupArrayAdapter adapter = new MySoupArrayAdapter(this, android.R.layout.simple_list_item_1, soups);
        mListView.setAdapter(adapter);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                @Override
//                public void onItemClickListener(new AdapterView.OnItemClickListener() {
//                    String soup = ((TextView) view).getText().toString();
//                    Toast.makeText(SoupsActivity.this,soup,Toast.LENGTH_LONG).show();
//                }
//            }
//            });
////                if (position == 0) {
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


            private void getSoups(String soup) {
                final RecipeService recipeService = new RecipeService();
                recipeService.findRecipes(soup, new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            String jsonData = response.body().string();
                            if (response.isSuccessful()) {
                                Log.v(TAG, jsonData);
                                soups = recipeService.processResults(response);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}