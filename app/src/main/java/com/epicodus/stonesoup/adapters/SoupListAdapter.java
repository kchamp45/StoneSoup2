package com.epicodus.stonesoup.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.models.Soup;
import com.epicodus.stonesoup.ui.SoupDetailActivity;
import com.epicodus.stonesoup.ui.SoupDetailFragment;
import com.epicodus.stonesoup.util.OnSoupSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SoupListAdapter extends RecyclerView.Adapter<SoupListAdapter.SoupViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Soup> mSoups = new ArrayList<>();
    private Context mContext;
    private OnSoupSelectedListener mOnSoupSelectedListener;

    public SoupListAdapter(Context context, ArrayList<Soup> soups, OnSoupSelectedListener soupSelectedListener) {
        mContext = context;
        mSoups = soups;
        mOnSoupSelectedListener = soupSelectedListener;
    }

    @Override
    public SoupListAdapter.SoupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soup_list_item, parent, false);
        SoupViewHolder viewHolder = new SoupViewHolder(view, mSoups, mOnSoupSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SoupListAdapter.SoupViewHolder holder, int position) {
        holder.bindSoup(mSoups.get(position));
    }

    @Override
    public int getItemCount() {
        return mSoups.size();
    }

    public class SoupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.soupNameTextView)
        TextView mNameTextView;
        @Bind(R.id.ratingTextView)
        TextView mRatingTextView;
        @Bind(R.id.prepTextView)
        TextView mPrepTextView;
        @Bind(R.id.soupImageView)
        ImageView mSoupImageView;
//        @Bind(R.id.ingredientTextView) TextView mIngredientTextView;

        private Context mContext;
        private int mOrientation;
        private ArrayList<Soup>mSoups = new ArrayList<>();
        private OnSoupSelectedListener mSoupSelectedListener;

        public SoupViewHolder(View itemView, ArrayList<Soup> soups, OnSoupSelectedListener soupSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();

            mOrientation = itemView.getResources().getConfiguration().orientation;
            mSoups = soups;
            mOnSoupSelectedListener = soupSelectedListener;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
            itemView.setOnClickListener(this);
        }

        public void bindSoup(Soup soup) {
            mNameTextView.setText(soup.getName());
            mRatingTextView.setText("Rating: " + soup.getRating() + "/5");
            mPrepTextView.setText("Total Prep Time: " + soup.getPrepTime() + " minutes");
            Picasso.with(mContext).load(soup.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mSoupImageView);

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            mOnSoupSelectedListener.onSoupSelected(itemPosition, mSoups, Constants.SOURCE_FIND);
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, SoupDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_SOUPS, Parcels.wrap(mSoups));
                intent.putExtra(Constants.KEY_SOURCE, Constants.SOURCE_FIND);
                mContext.startActivity(intent);
            }
        }

        private void createDetailFragment(int position) {
            SoupDetailFragment detailFragment = SoupDetailFragment.newInstance(mSoups, position, Constants.SOURCE_FIND);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.soupDetailContainer, detailFragment);
            ft.commit();
        }

    }
}