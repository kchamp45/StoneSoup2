package com.epicodus.stonesoup.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.adapters.SoupPagerAdapter;
import com.epicodus.stonesoup.models.Soup;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SoupDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private SoupPagerAdapter adapterViewPager;
    ArrayList<Soup> mSoups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soup_detail);
        ButterKnife.bind(this);

        mSoups = Parcels.unwrap(getIntent().getParcelableExtra("soups"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new SoupPagerAdapter(getSupportFragmentManager(), mSoups);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
