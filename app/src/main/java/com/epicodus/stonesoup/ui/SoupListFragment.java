package com.epicodus.stonesoup.ui;


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

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SoupListFragment extends Fragment {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRestriction;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private SoupListAdapter mAdapter;

    public ArrayList<Soup> soups = new ArrayList<>();

    public SoupListFragment() {
        // Required empty public constructor
    }

   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       Intent intent = getActivity().getIntent();
       String soup = intent.getStringExtra("soup");
       String restriction = intent.getStringExtra("restriction");

       getSoups(soup, restriction);

       mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
       mEditor = mSharedPreferences.edit();
       mRestriction = mSharedPreferences.getString(Constants.PREFERENCES_RESTRICTION_KEY, null);
       if (mRestriction != null) {
           getSoups(soup, mRestriction);
       }
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
                        mAdapter = new SoupListAdapter(getActivity(), soups);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        for(Soup soup : soups){
                            soup.getImageUrl();
                        }

                    }
                });
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_soup_list, container, false);
        ButterKnife.bind(this, view);
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
