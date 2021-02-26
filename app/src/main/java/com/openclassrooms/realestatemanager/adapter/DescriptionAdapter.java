package com.openclassrooms.realestatemanager.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NIATEL Brice on 26/02/2021.
 */
public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.ViewHolder> {
    private List<String> mListImagePath = new ArrayList<>();

    private final Activity mActivity;

    private final InternalFilesRepository mInternalFilesRepository;

    public DescriptionAdapter(Activity activity) {
        this.mActivity = activity;
        mInternalFilesRepository = ((MyApplication) mActivity.getApplicationContext()).getContainerDependencies().getInternalFilesRepository();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_description_image, parent, false);
        return new DescriptionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bitmap image = mInternalFilesRepository.getFile(mListImagePath.get(position));
        Glide.with(mActivity)
                .load(image)
                .centerCrop()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mListImagePath.size();
    }

    public void setItems(List<String> list) {
        mListImagePath = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.description_image);
        }
    }
}


