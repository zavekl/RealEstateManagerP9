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
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.activity.MainActivity;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.fragment.DescriptionRealEstateFragment;
import com.openclassrooms.realestatemanager.fragment.RVListRealEstateFragment;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.ApplicationPreferencesRepo;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <NIATEL Brice> on <16/02/2020>.
 */
public class ListRealEstateRVAdapter extends RecyclerView.Adapter<ListRealEstateRVAdapter.ViewHolder> {
    private static final String TAG = "ListRERVAdapter";
    public static final String BUNDLE_ID_DESCRIPTION = "BUNDLE_ID";


    @NonNull
    private List<RealEstate> mItemRealEstate = new ArrayList<>();

    private final Activity mActivity;

    private final InternalFilesRepository mInternalFilesRepository;

    private final RVListRealEstateFragment mRvListRealEstateFragment;
    private final ApplicationPreferencesRepo mApplicationPreferencesRepo;

    public ListRealEstateRVAdapter(Activity mActivity, RVListRealEstateFragment rvListRealEstateFragment) {
        Log.d(TAG, "ListRealEstateRVAdapter: ");
        this.mActivity = mActivity;
        mRvListRealEstateFragment = rvListRealEstateFragment;
        mInternalFilesRepository = ((MyApplication) mActivity.getApplicationContext()).getContainerDependencies().getInternalFilesRepository();
        mApplicationPreferencesRepo = ((MyApplication) mActivity.getApplicationContext()).getContainerDependencies().getApplicationPreferencesRepo();
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
        if (position == 0 && MainActivity.mTabletMode) {
            Log.d(TAG, "onBindViewHolder: tablet mode for description activity");
            mApplicationPreferencesRepo.setSharedPrefsFirstItemDescription(String.valueOf(mItemRealEstate.get(position).getId()));
            if (MainActivity.mTabletMode && !mApplicationPreferencesRepo.getSharedPrefsPhotoIntent()) {
                Log.d(TAG, "onBindViewHolder: set first item" + mApplicationPreferencesRepo.getSharedPrefsPhotoIntent());
                final DescriptionRealEstateFragment fragment = DescriptionRealEstateFragment.newInstance();

                final Bundle bundle = new Bundle();
                bundle.putLong(BUNDLE_ID_DESCRIPTION, mItemRealEstate.get(position).getId());

                fragment.setArguments(bundle);

                mRvListRealEstateFragment.getParentFragmentManager().beginTransaction()
                        .replace(R.id.description_fragment, fragment, String.valueOf(mItemRealEstate.get(position).getId()))
                        .commit();
            }
            mApplicationPreferencesRepo.deleteSharedPrefsPhotoIntent();
        }

        //Type of real estate
        holder.mType.setText(mItemRealEstate.get(position).getType());

        //Address
        holder.mTown.setText(mItemRealEstate.get(position).getAddress().getTown());

        //Price
        holder.mPrice.setText(String.valueOf(mItemRealEstate.get(position).getPrice()));

        //Image
        Bitmap image = mInternalFilesRepository.getFile(mItemRealEstate.get(position).getListPathImage().get(0));
        Log.d(TAG, "onBindViewHolder: " + image);
        Glide.with(mActivity)
                .load(image)
                .centerCrop()
                .into(holder.mImageView);

        if (!mItemRealEstate.get(position).isAvailability()) {
            holder.mTextView.setVisibility(View.VISIBLE);
            holder.mTextView.bringToFront();
        } else {
            holder.mTextView.setVisibility(View.INVISIBLE);
        }


        //On click of image start DescriptionActivity
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.displayDescriptionFragment();
                final DescriptionRealEstateFragment fragment = DescriptionRealEstateFragment.newInstance();

                final Bundle bundle = new Bundle();
                bundle.putLong(BUNDLE_ID_DESCRIPTION, mItemRealEstate.get(position).getId());

                fragment.setArguments(bundle);

                mRvListRealEstateFragment.getParentFragmentManager().beginTransaction()
                        .replace(R.id.description_fragment, fragment, String.valueOf(mItemRealEstate.get(position).getId()))
                        .commit();

                if (!MainActivity.mTabletMode) {
                    Log.d(TAG, "onClick: phone mode");
                    MainActivity.hideViewPager();
                }
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
        final TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mType = itemView.findViewById(R.id.rv_tv_item_type);
            mTown = itemView.findViewById(R.id.rv_tv_item_town);
            mPrice = itemView.findViewById(R.id.rv_tv_item_price);
            mImageView = itemView.findViewById(R.id.rv_iv_photo);
            mTextView = itemView.findViewById(R.id.tv_item_description);
        }
    }
}