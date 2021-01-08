package com.openclassrooms.realestatemanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by <NIATEL Brice> on <16/02/2020>.
 */
class ListRealEstateRVAdapter extends RecyclerView.Adapter<ListRealEstateRVAdapter.ViewHolder> {
    private final List<RealEstate> mItemRealEstate;

    private final Context mContext;

    public ListRealEstateRVAdapter(List<RealEstate> mItemRealEstate, Context context) {
        this.mItemRealEstate = mItemRealEstate;
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
        holder.mType.setText(mItemRealEstate.get(position).getType());
        holder.mTown.setText(mItemRealEstate.get(position).getAddress());

        String price = String.valueOf(mItemRealEstate.get(position).getPrice());
        holder.mPrice.setText(price);

        Glide.with(mContext)
                .load(mItemRealEstate.get(position).getDrawable())
                .centerCrop()
                .into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DescriptionRealEstateActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mItemRealEstate != null) {
            if (mItemRealEstate.size() == 0) {
                return 0;
            } else {
                return mItemRealEstate.size();
            }
        } else {
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
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
