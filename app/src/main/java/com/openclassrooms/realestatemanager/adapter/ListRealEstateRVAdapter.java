package com.openclassrooms.realestatemanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.activity.DescriptionRealEstateActivity;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <NIATEL Brice> on <16/02/2020>.
 */
public class ListRealEstateRVAdapter extends RecyclerView.Adapter<ListRealEstateRVAdapter.ViewHolder> {
    private static final String TAG = "ListRERVAdapter";
    private List<RealEstate> mItemRealEstate = new ArrayList<>();
    private final Context mContext;

    public ListRealEstateRVAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ListRealEstateRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_real_estate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: item : " + position);
        holder.mType.setText(mItemRealEstate.get(position).getType());
        holder.mTown.setText(mItemRealEstate.get(position).getAddress());

        String price = String.valueOf(mItemRealEstate.get(position).getPrice());
        holder.mPrice.setText(price);

        Glide.with(mContext)
                .load(Utils.StringToBitMap(mItemRealEstate.get(position).getImage()))
                .centerCrop()
                .into(holder.mImageView);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DescriptionRealEstateActivity.class);
                Log.d(TAG, "onClick: " + mItemRealEstate.get(position).getId());
                intent.putExtra("id", mItemRealEstate.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    public void setRealEstate(List<RealEstate> realEstate) {
        this.mItemRealEstate = realEstate;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItemRealEstate.size();
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
