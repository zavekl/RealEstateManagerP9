package com.openclassrooms.realestatemanager.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.Constants;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.activity.MainActivity;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.fragment.DescriptionRealEstateFragment;
import com.openclassrooms.realestatemanager.fragment.RVListRealEstateFragment;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <NIATEL Brice> on <16/02/2020>.
 */
public class ListRealEstateRVAdapter extends RecyclerView.Adapter<ListRealEstateRVAdapter.ViewHolder> {
    private static final String TAG = "ListRERVAdapter";

    private List<RealEstate> mItemRealEstate = new ArrayList<>();

    private final Activity mActivity;

    private final InternalFilesRepository mInternalFilesRepository;

    private final RVListRealEstateFragment mRvListRealEstateFragment;

    public ListRealEstateRVAdapter(Activity mActivity, RVListRealEstateFragment rvListRealEstateFragment) {
        Log.d(TAG, "ListRealEstateRVAdapter: ");
        this.mActivity = mActivity;
        mRvListRealEstateFragment = rvListRealEstateFragment;
        mInternalFilesRepository = ((MyApplication) mActivity.getApplicationContext()).getContainerDependencies().getInternalFilesRepository();
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
        //Type of real estate
        holder.mType.setText(mItemRealEstate.get(position).getType());

        //Address
        holder.mTown.setText(mItemRealEstate.get(position).getAddress().getTown());

        //Price
        holder.mPrice.setText(String.valueOf(mItemRealEstate.get(position).getPrice()));

        //Image
        Bitmap image = mInternalFilesRepository.getFile(mItemRealEstate.get(position).getImage());
        Log.d(TAG, "onBindViewHolder: " + image);
        Glide.with(mActivity)
                .load(image)
                .centerCrop()
                .into(holder.mImageView);

        //On click of image start DescriptionActivity
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescriptionRealEstateFragment fragment = DescriptionRealEstateFragment.newInstance();

                Bundle bundle = new Bundle();
                bundle.putLong(Constants.BUNDLE_ID, mItemRealEstate.get(position).getId());

                fragment.setArguments(bundle);

                mRvListRealEstateFragment.getParentFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .add(R.id.description_fragment, fragment, null)
                        .commit();

                MainActivity.hideViewPager();
            }
        });
    }

    public void setItems(List<RealEstate> realEstates) {
        mItemRealEstate = realEstates;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mItemRealEstate.size());
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