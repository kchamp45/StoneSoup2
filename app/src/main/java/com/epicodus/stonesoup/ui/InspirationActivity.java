package com.epicodus.stonesoup.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InspirationActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.soupEditText) EditText mSoupEditText;
    @Bind(R.id.restrictionEditText) EditText mRestrictionEditText;
    @Bind(R.id.find_recipe_button) Button mFindRecipesButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspiration);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface fancyFont = Typeface.createFromAsset(getAssets(), "fonts/CaviarDreams.ttf");
        mAppNameTextView.setTypeface(fancyFont);

        mFindRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soup = mSoupEditText.getText().toString();
                if (v == mFindRecipesButton) {
                    String excludedIngredient = mRestrictionEditText.getText().toString();
                    if (!(excludedIngredient).equals("")) {
                        addToSharedPreferences(excludedIngredient);
                    }

                    Intent intent = new Intent(InspirationActivity.this, SoupListActivity.class);
                    intent.putExtra("soup", soup);
                    intent.putExtra("restriction", excludedIngredient);
                    startActivity(intent);
                    mSoupEditText.getText().clear();
                    mRestrictionEditText.getText().clear();

                }

            }
        });
    }
    private void addToSharedPreferences(String excludedIngredient) {
        mEditor.putString(Constants.PREFERENCES_RESTRICTION_KEY, excludedIngredient).apply();
    }
}


