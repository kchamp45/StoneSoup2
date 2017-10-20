package com.epicodus.stonesoup.adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.models.Soup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SoupListAdapter extends RecyclerView.Adapter<SoupListAdapter.SoupViewHolder> {
    private ArrayList<Soup> mSoups = new ArrayList<>();
    private Context mContext;

    public SoupListAdapter(Context context, ArrayList<Soup> soups) {
        mContext = context;
        mSoups = soups;
    }
    @Override
    public SoupListAdapter.SoupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soup_list_item, parent, false);
        SoupViewHolder viewHolder = new SoupViewHolder(view);
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

    public class SoupViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.soupNameTextView) TextView mNameTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        @Bind(R.id.prepTextView) TextView mPrepTextView;

        private Context mContext;

        public SoupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindSoup(Soup soup) {
            mNameTextView.setText(soup.getName());
            mRatingTextView.setText("Rating: " + soup.getRating() + "/5");
            mPrepTextView.setText("Total Prep Time: " + soup.getPrepTime() + " minutes");

        }
    }

}
