package com.epicodus.stonesoup.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.adapters.FirebaseSoupListAdapter;
import com.epicodus.stonesoup.adapters.FirebaseSoupViewHolder;
import com.epicodus.stonesoup.models.Soup;
import com.epicodus.stonesoup.util.OnStartDragListener;
import com.epicodus.stonesoup.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedSoupListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mSoupReference;
    private FirebaseSoupListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;


    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soups);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_SOUPS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);


        mSoupReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_SOUPS)
                .child(uid);

        mFirebaseAdapter = new FirebaseSoupListAdapter(Soup.class,
                R.layout.soup_list_item_drag, FirebaseSoupViewHolder.class,
                mSoupReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
