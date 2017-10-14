package com.epicodus.stonesoup;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by kimlu on 10/13/17.
 */

public class MyRecipesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String [] mRecipes;

    public MyRecipesArrayAdapter(Context mContext, int resource, String[] mRecipes) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mRecipes = mRecipes;
    }

    @Override
        public Object getItem(int position) {
        String recipe = mRecipes[position];
        return recipe;
        }

    @Override
        public int getCount() {
            return mRecipes.length;
        }
}
