package com.openclassrooms.realestatemanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by <NIATEL Brice> on <16/02/2020>.
 */
class ListRealEstateRVAdapter extends RecyclerView.Adapter<ListRealEstateRVAdapter.ViewHolder> {
    private static final String TAG = "ListRealEstateRVAdapter";
    private final List<RealEstate> mItemRealEstate = Arrays.asList(
            new RealEstate("Maison1",121212.50f,520.25f,16,"Description1",
                    "adresse1","Rien",false,"12/2/2019","0","Jean"),
            new RealEstate("Maison2",121212.50f,520.25f,16,"Description2",
                    "adresse2","Rien",false,"12/2/2019","0","Jean"));

    @NonNull
    @Override
    public ListRealEstateRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_real_estate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRealEstateRVAdapter.ViewHolder holder, int position) {
        holder.mType.setText(mItemRealEstate.get(position).getmType());
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mType = itemView.findViewById(R.id.item_type);
        }
    }
}
