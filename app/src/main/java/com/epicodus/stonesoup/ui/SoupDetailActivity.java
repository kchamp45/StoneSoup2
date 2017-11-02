package com.epicodus.stonesoup.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.stonesoup.Constants;
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
    private String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soup_detail);
        ButterKnife.bind(this);
        mSource = getIntent().getStringExtra(Constants.KEY_SOURCE);

        mSoups = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_SOUPS));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);

        adapterViewPager = new SoupPagerAdapter(getSupportFragmentManager(), mSoups, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
