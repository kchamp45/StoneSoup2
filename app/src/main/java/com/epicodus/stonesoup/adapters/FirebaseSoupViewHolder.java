package com.epicodus.stonesoup.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.models.Soup;
import com.epicodus.stonesoup.ui.SoupDetailActivity;
import com.epicodus.stonesoup.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import static com.epicodus.stonesoup.R.id.soupNameTextView;

public class FirebaseSoupViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    View mView;
    Context mContext;
    public TextView mSoupTextView;

    public FirebaseSoupViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindSoup(Soup soup) {
        mSoupTextView = (TextView) mView.findViewById(soupNameTextView);
        TextView nameTextView = (TextView) mView.findViewById(soupNameTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        TextView prepTextView = (TextView) mView.findViewById(R.id.prepTextView);
        TextView restrictionTextView = (TextView) mView.findViewById(R.id.restrictionTextView);

        nameTextView.setText(soup.getName());
        ratingTextView.setText("Rating: " + soup.getRating() + "/5");
        restrictionTextView.setText("Excludes: " + soup.getRestriction());
    }

    @Override
    public void onItemSelected(){
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear(){
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
    }

}
