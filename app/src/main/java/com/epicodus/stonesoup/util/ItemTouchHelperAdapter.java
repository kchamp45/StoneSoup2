package com.epicodus.stonesoup.util;

/**
 * Created by kimlu on 10/30/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
