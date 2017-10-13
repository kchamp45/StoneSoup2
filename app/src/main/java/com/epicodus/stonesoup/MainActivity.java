package com.epicodus.stonesoup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.epicodus.stonesoup.R.id.folklore_button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.folklore_button) Button mFolkloreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFolkloreButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFolkloreButton){
            Intent intent = new Intent(MainActivity.this, FolkloreActivity.class);
            startActivity(intent);
        }

    }

}
