package com.epicodus.stonesoup;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by kimlu on 10/13/17.
 */

public class MyKitchensArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String [] mKitchens;
    private String [] mKitLocations;

    public MyKitchensArrayAdapter(Context mContext, int resource, String[] mKitchens, String[] mKitLocations) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mKitchens = mKitchens;
        this.mKitLocations = mKitLocations;
    }
    @Override
    public Object getItem(int position) {
        String kitchen = mKitchens[position];
        String kitLocation = mKitLocations[position];
        return String.format("%s \nLocation: %s", kitchen, kitLocation);
    }

    @Override
    public int getCount() {
        return mKitchens.length;
    }
}
