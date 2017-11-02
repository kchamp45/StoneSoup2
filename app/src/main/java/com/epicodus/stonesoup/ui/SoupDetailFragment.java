package com.epicodus.stonesoup.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.models.Soup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SoupDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.soupNameTextView)
    TextView mNameLabel;
    @Bind(R.id.ingredientTextView)
    TextView mIngredientLabel;
    @Bind(R.id.recipeTextView)
    TextView mRecipeLabel;
    @Bind(R.id.restrictionTextView) TextView mRestrictionLabel;
    @Bind(R.id.saveSoupButton)
    TextView mSaveSoupButton;

    private Soup mSoup;
    private ArrayList<Soup> mSoups;
    private int mPosition;

    public SoupDetailFragment() {
    }

    public static SoupDetailFragment newInstance(ArrayList<Soup> soups, Integer position) {
        SoupDetailFragment soupDetailFragment = new SoupDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_SOUPS, Parcels.wrap(soups));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        soupDetailFragment.setArguments(args);
        return soupDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSoups = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_SOUPS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mSoup = mSoups.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soup_detail, container, false);
        ButterKnife.bind(this, view);

        mNameLabel.setText(mSoup.getName());
        mIngredientLabel.setText("INGREDIENTS: " + android.text.TextUtils.join(", ", mSoup.getIngredients()));
        mRestrictionLabel.setText("RESTRICTIONS: No " + mSoup.getRestriction());


        mRecipeLabel.setOnClickListener(this);

        mSaveSoupButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveSoupButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference soupRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_SOUPS)
                    .child(uid);

            DatabaseReference pushRef = soupRef.push();
            String pushId = pushRef.getKey();
            mSoup.setPushId(pushId);
            pushRef.setValue(mSoup);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        if (v == mRecipeLabel) {
            Uri webpage = Uri.parse("http://www.allrecipes.com" + "/search/results/?wt=" + mSoup.getName());
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }
    }
}
