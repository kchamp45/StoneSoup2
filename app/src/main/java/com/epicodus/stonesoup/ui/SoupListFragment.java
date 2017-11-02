package com.epicodus.stonesoup.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.adapters.SoupListAdapter;
import com.epicodus.stonesoup.models.Soup;
import com.epicodus.stonesoup.services.RecipeService;
import com.epicodus.stonesoup.util.OnSoupSelectedListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SoupListFragment extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private SoupListAdapter mAdapter;
    public ArrayList<Soup> soups = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRestriction;
    private OnSoupSelectedListener mOnSoupSelectedListener;
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            mOnSoupSelectedListener = (OnSoupSelectedListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ e.getMessage());
        }
    }


    public SoupListFragment() {

    }

   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
       mEditor = mSharedPreferences.edit();

       setHasOptionsMenu(true);
   }

    private void getSoups(String soup, String restriction) {
        final RecipeService recipeService = new RecipeService();
        recipeService.findRecipes(soup, restriction, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                soups = recipeService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new SoupListAdapter(getActivity(), soups, mOnSoupSelectedListener);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }
                });
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_soup_list, container, false);
        ButterKnife.bind(this, view);

        Intent intent = getActivity().getIntent();
        String soup = intent.getStringExtra("soup");
        String restriction = intent.getStringExtra("restriction");

        getSoups(soup, restriction);

        mRestriction = mSharedPreferences.getString(Constants.PREFERENCES_RESTRICTION_KEY, null);
        if (mRestriction != null) {
            getSoups(soup, mRestriction);
        }
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_RESTRICTION_KEY, location).apply();
    }
}
