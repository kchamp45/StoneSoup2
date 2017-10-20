package com.epicodus.stonesoup.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.models.Soup;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SoupDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.soupNameTextView)
    TextView mNameLabel;
    @Bind(R.id.ingredientTextView)
    TextView mIngredientLabel;
    @Bind(R.id.recipeTextView)
    TextView mRecipeLabel;
    @Bind(R.id.imageTextView) TextView mImageLabel;
    @Bind(R.id.saveSoupButton)
    TextView mSaveSoupButton;

    private Soup mSoup;

    public SoupDetailFragment() {
    }

    public static SoupDetailFragment newInstance(Soup soup) {
        SoupDetailFragment soupDetailFragment = new SoupDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("soup", Parcels.wrap(soup));
        soupDetailFragment.setArguments(args);
        return soupDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSoup = Parcels.unwrap(getArguments().getParcelable("soup"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_soup_detail, container, false);
        ButterKnife.bind(this, view);

        mNameLabel.setText(mSoup.getName());
        mIngredientLabel.setText("INGREDIENTS: " + android.text.TextUtils.join(", ", mSoup.getIngredients()));

        mImageLabel.setOnClickListener(this);
        mRecipeLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mRecipeLabel) {
            Uri webpage = Uri.parse("http://www.allrecipes.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }

        if(v == mImageLabel){
            Intent imageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mSoup.getImageUrl()));
            startActivity(imageIntent);
        }
    }
}
