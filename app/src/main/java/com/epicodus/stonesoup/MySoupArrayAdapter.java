package com.epicodus.stonesoup;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by kimlu on 10/13/17.
 */

public class MySoupArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mSoups;


    public MySoupArrayAdapter(Context mContext, int resource, String[] mSoups) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mSoups = mSoups;

    }
    @Override
    public Object getItem(int position) {
        String soup = mSoups[position];
        return String.format("%s", soup);
    }

    @Override
    public int getCount() {
        return mSoups.length;
    }
}
