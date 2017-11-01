package com.epicodus.stonesoup.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.stonesoup.Constants;
import com.epicodus.stonesoup.R;
import com.epicodus.stonesoup.models.Soup;
import com.epicodus.stonesoup.ui.SoupDetailActivity;
import com.epicodus.stonesoup.ui.SoupDetailFragment;
import com.epicodus.stonesoup.util.ItemTouchHelperAdapter;
import com.epicodus.stonesoup.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class  FirebaseSoupListAdapter extends FirebaseRecyclerAdapter<Soup, FirebaseSoupViewHolder> implements ItemTouchHelperAdapter {
    private ChildEventListener mChildEventListener;
    private ArrayList<Soup> mSoups = new ArrayList<>();

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private int mOrientation;

    public FirebaseSoupListAdapter(Class<Soup> modelClass, int modelLayout,
                                   Class<FirebaseSoupViewHolder> viewHolderClass,
                                   Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mSoups.add(dataSnapshot.getValue(Soup.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseSoupViewHolder viewHolder, Soup model, int position) {
        viewHolder.bindSoup(model);

        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;
        if(mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }
        viewHolder.mSoupTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, SoupDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, viewHolder.getAdapterPosition());
                    intent.putExtra(Constants.EXTRA_KEY_SOUPS, Parcels.wrap(mSoups));
                    mContext.startActivity(intent);
                }
            }
        });
    }

    private void createDetailFragment(int position) {

        SoupDetailFragment detailFragment = SoupDetailFragment.newInstance(mSoups, position);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.soupDetailContainer, detailFragment);
        ft.commit();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mSoups, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mSoups.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
            for (Soup soup : mSoups) {
                int index = mSoups.indexOf(soup);
                DatabaseReference ref = getRef(index);
                soup.setIndex(Integer.toString(index));
                ref.setValue(soup);
            }
        }
        @Override
        public void cleanup() {
            super.cleanup();
            setIndexInFirebase();
            mRef.removeEventListener(mChildEventListener);
        }
    }