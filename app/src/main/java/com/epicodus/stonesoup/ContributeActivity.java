package com.epicodus.stonesoup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContributeActivity extends AppCompatActivity {
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.find_kitchen_button) Button mFindKitchensButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);

        ButterKnife.bind(this);

        mFindKitchensButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                if (location.length() != 5) {
                    mLocationEditText.setError("Please enter a 5 digit zipcode");
                }else{
                    Intent intent = new Intent(ContributeActivity.this, KitchensActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                    mLocationEditText.getText().clear();
                }

            }

        });

    }

}
