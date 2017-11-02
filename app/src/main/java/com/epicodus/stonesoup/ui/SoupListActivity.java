package com.epicodus.stonesoup.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.adapters.SoupListAdapter;
import com.epicodus.stonesoup.models.Soup;
import com.epicodus.stonesoup.services.RecipeService;
import com.epicodus.stonesoup.util.OnSoupSelectedListener;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class SoupListActivity extends AppCompatActivity implements OnSoupSelectedListener {
    private Integer mPosition;
    ArrayList<Soup>mSoups;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRestriction;
    String mSource;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private SoupListAdapter mAdapter;

    public ArrayList<Soup> soups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soups);

        if (savedInstanceState != null){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mSoups = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_SOUPS));
                mSource = savedInstanceState.getString(Constants.KEY_SOURCE);

                if (mPosition != null && mSoups != null) {
                    Intent intent = new Intent(this, SoupDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_SOUPS, Parcels.wrap(mSoups));
                    intent.putExtra(Constants.KEY_SOURCE, mSource);
                    startActivity(intent);
                }
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mSoups != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_SOUPS, Parcels.wrap(mSoups));
            outState.putString(Constants.KEY_SOURCE, mSource);
        }

    }

    @Override
    public void onSoupSelected(Integer position, ArrayList<Soup> soups, String source) {
        mPosition = position;
        mSoups = soups;
        mSource = source;
    }
}

