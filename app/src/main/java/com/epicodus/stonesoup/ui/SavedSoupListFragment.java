package com.epicodus.stonesoup.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.adapters.FirebaseSoupListAdapter;
import com.epicodus.stonesoup.adapters.FirebaseSoupViewHolder;
import com.epicodus.stonesoup.models.Soup;
import com.epicodus.stonesoup.util.OnStartDragListener;
import com.epicodus.stonesoup.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedSoupListFragment extends Fragment implements OnStartDragListener {
    private DatabaseReference mSoupReference;
    private FirebaseSoupListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;


    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;


    public SavedSoupListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_soup_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;

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

        Log.v("here", mSoupReference + "");

        mFirebaseAdapter = new FirebaseSoupListAdapter(Soup.class,
                R.layout.soup_list_item_drag, FirebaseSoupViewHolder.class,
                mSoupReference, this, getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}
