package com.openclassrooms.realestatemanager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by NIATEL Brice on 17/02/2021.
 */
public class AddGridViewAdapter extends BaseAdapter {
    private static final String TAG = "AddRVAdapter";

    private final LayoutInflater mLayoutInflater;

    private final Context mContext;

    private final List<Bitmap> mImageList;

    public AddGridViewAdapter(@NonNull List<Bitmap> mImageList, Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.mImageList = mImageList;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: " + mImageList.size());
        return mImageList.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d(TAG, "getItem: " + position);
        return mImageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Log.d(TAG, "getView: " + position);
            convertView = mLayoutInflater.inflate(R.layout.rv_item_add, parent, false);
            ImageView imageView = convertView.findViewById(R.id.iv_item);

            Glide.with(mContext)
                    .load(mImageList.get(position))
                    .centerCrop()
                    .into(imageView);
        }
        return convertView;
    }

    public void addItem(Bitmap b) {
        mImageList.add(b);
        if (getCount() != 7 && getCount() <= 6) {
            Collections.swap(mImageList, getCount() - 1, getCount() - 2);
        } else mImageList.set(5, b);
    }
}
