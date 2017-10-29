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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseSoupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseSoupViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindSoup(Soup soup) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.soupNameTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        TextView prepTextView = (TextView) mView.findViewById(R.id.prepTextView);
        TextView restrictionTextView = (TextView) mView.findViewById(R.id.restrictionTextView);

        nameTextView.setText(soup.getName());
        ratingTextView.setText("Rating: " + soup.getRating() + "/5");
        restrictionTextView.setText("Excludes: " + soup.getRestriction());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Soup> soups = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SOUPS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    soups.add(snapshot.getValue(Soup.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, SoupDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("soups", Parcels.wrap(soups));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
