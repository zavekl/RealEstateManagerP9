package com.openclassrooms.realestatemanager.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.ListRealEstateRVAdapter;
import com.openclassrooms.realestatemanager.bdd.RealEstateDao;
import com.openclassrooms.realestatemanager.bdd.RealEstateDatabase;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.Utils;
import com.openclassrooms.realestatemanager.viewmodel.RVListRealEstateViewModel;

import java.util.List;

public class RVListRealEstateFragment extends Fragment {
    private static final String TAG = "RVListREFragment";

    private RecyclerView mRecyclerView;
    private ListRealEstateRVAdapter mAdapter;
    private RVListRealEstateViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rv_real_estate, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider((ViewModelStoreOwner) this, new ViewModelProvider.NewInstanceFactory()).get(RVListRealEstateViewModel.class);
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())).
                get(RVListRealEstateViewModel.class);

        mViewModel.getAllRealestates().observe(requireActivity(), new Observer<List<RealEstate>>() {
            @Override
            public void onChanged(List<RealEstate> realEstates) {
                Log.d(TAG, "onChanged: " + realEstates);
                mAdapter.setRealEstate(realEstates);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_real_estate);
        mAdapter = new ListRealEstateRVAdapter(requireContext());
        mRecyclerView.setAdapter(mAdapter);
    }
}