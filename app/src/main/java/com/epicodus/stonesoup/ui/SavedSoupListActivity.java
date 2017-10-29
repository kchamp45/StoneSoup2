package com.epicodus.stonesoup.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.adapters.FirebaseSoupViewHolder;
import com.epicodus.stonesoup.models.Soup;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedSoupListActivity extends AppCompatActivity {
    private DatabaseReference mSoupReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soups);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mSoupReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SOUPS).child(uid);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Soup, FirebaseSoupViewHolder>
                (Soup.class, R.layout.soup_list_item, FirebaseSoupViewHolder.class,
                        mSoupReference) {

            @Override
            protected void populateViewHolder(FirebaseSoupViewHolder viewHolder,
                                              Soup model, int position) {
                viewHolder.bindSoup(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
}
