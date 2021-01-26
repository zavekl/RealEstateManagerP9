package com.openclassrooms.realestatemanager.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.activity.DescriptionRealEstateActivity;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;
import com.openclassrooms.realestatemanager.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <NIATEL Brice> on <16/02/2020>.
 */
public class ListRealEstateRVAdapter extends RecyclerView.Adapter<ListRealEstateRVAdapter.ViewHolder> {
    private static final String TAG = "ListRERVAdapter";
    private List<RealEstate> mItemRealEstate = new ArrayList<>();
    private List<Long> mItemIdRealEstate = new ArrayList<>();

    private final Activity activity;
    private final RealEstateRepository mRealEstateRepository;

    public ListRealEstateRVAdapter(Activity activity) {
        Log.d(TAG, "ListRealEstateRVAdapter: ");
        this.activity = activity;
        mRealEstateRepository = ((MyApplication) activity.getApplicationContext()).getContainerDependencies().getRealEstateRepository();
        mRealEstateRepository.getAllIdRealEstate().observe((LifecycleOwner) this.activity, new Observer<List<Long>>() {
            @Override
            public void onChanged(List<Long> longs) {
                Log.d(TAG, "onChanged: " + longs);
                mItemIdRealEstate = longs;
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public ListRealEstateRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_real_estate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: item : " + position);
        mRealEstateRepository.getRealEstateById(mItemIdRealEstate.get(position)).observe((LifecycleOwner) activity, new Observer<RealEstate>() {
            @Override
            public void onChanged(RealEstate realEstate) {
                holder.mType.setText(realEstate.getType());
                holder.mTown.setText(realEstate.getAddress());
                holder.mPrice.setText(String.valueOf(realEstate.getPrice()));

                Glide.with(activity)
                        .load(Utils.StringToBitMap(realEstate.getImage()))
                        .centerCrop()
                        .into(holder.mImageView);
            }
        });

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DescriptionRealEstateActivity.class);
                Log.d(TAG, "onClick: " + mItemIdRealEstate.get(position));
                intent.putExtra("id", mItemIdRealEstate.get(position));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return mItemIdRealEstate.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mType;
        final TextView mTown;
        final TextView mPrice;
        final ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mType = itemView.findViewById(R.id.rv_tv_item_type);
            mTown = itemView.findViewById(R.id.rv_tv_item_town);
            mPrice = itemView.findViewById(R.id.rv_tv_item_price);
            mImageView = itemView.findViewById(R.id.rv_iv_photo);
        }
    }
}
