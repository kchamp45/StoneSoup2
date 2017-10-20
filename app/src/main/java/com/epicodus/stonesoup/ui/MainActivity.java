package com.epicodus.stonesoup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epicodus.stonesoup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.folklore_button) Button mFolkloreButton;
    @Bind(R.id.soup_button) Button mSoupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFolkloreButton.setOnClickListener(this);
        mSoupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFolkloreButton){
            Intent intent = new Intent(MainActivity.this, FolkloreActivity.class);
            startActivity(intent);
        }else if(v == mSoupButton) {
            Intent intent = new Intent(MainActivity.this, InspirationActivity.class);
            startActivity(intent);
        }
    }

}
