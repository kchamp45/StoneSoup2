package com.epicodus.stonesoup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.stonesoup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InspirationActivity extends AppCompatActivity {
    @Bind(R.id.soupEditText) EditText mSoupEditText;
    @Bind(R.id.find_recipe_button) Button mFindRecipesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspiration);

        ButterKnife.bind(this);

        mFindRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soup = mSoupEditText.getText().toString();

                    Intent intent = new Intent(InspirationActivity.this, SoupListActivity.class);
                    intent.putExtra("soup", soup);
                    startActivity(intent);
                    mSoupEditText.getText().clear();

            }

        });

    }

}
