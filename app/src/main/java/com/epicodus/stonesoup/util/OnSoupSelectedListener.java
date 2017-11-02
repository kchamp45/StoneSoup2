package com.epicodus.stonesoup.util;

import com.epicodus.stonesoup.models.Soup;

import java.util.ArrayList;

/**
 * Created by Guest on 11/2/17.
 */

public interface OnSoupSelectedListener {
        public void onSoupSelected(Integer position, ArrayList<Soup> soups, String source);

}
