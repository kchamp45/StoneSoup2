package com.epicodus.stonesoup.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.stonesoup.models.Soup;
import com.epicodus.stonesoup.ui.SoupDetailFragment;

import java.util.ArrayList;

public class SoupPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Soup> mSoups;
    private String mSource;

    public SoupPagerAdapter(FragmentManager fm, ArrayList<Soup> soups, String source) {
        super(fm);
        mSoups = soups;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return SoupDetailFragment.newInstance(mSoups, position, mSource);
    }

    @Override
    public int getCount(){
        return mSoups.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mSoups.get(position).getName();
    }
}
