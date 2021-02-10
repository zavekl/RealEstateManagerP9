package com.openclassrooms.realestatemanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.activity.MainActivity;
import com.openclassrooms.realestatemanager.adapter.ListRealEstateRVAdapter;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.utils.VerticalSpaceItemDecoration;
import com.openclassrooms.realestatemanager.viewmodel.RVListRealEstateViewModel;

import java.util.List;

public class RVListRealEstateFragment extends Fragment {
    private ListRealEstateRVAdapter mAdapter;

    private FloatingActionButton mFAB;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rv_real_estate_fragment, container, false);

        mFAB = view.findViewById(R.id.rv_fab);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        RVListRealEstateViewModel mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())).
//                get(RVListRealEstateViewModel.class);
        RVListRealEstateViewModel mViewModel = new ViewModelProvider(this).get(RVListRealEstateViewModel.class);

        mViewModel.getAllRealEstate().observe((LifecycleOwner) requireContext(), new Observer<List<RealEstate>>() {
            @Override
            public void onChanged(List<RealEstate> realEstates) {
                mAdapter.setItems(realEstates);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_real_estate);
        mAdapter = new ListRealEstateRVAdapter(requireActivity(), this);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(15));
        mRecyclerView.setAdapter(mAdapter);

        onScrollRecyclerView(mRecyclerView);

        onClickFAB();
    }

    //On click of FAB run AddRealEstateFragment
    private void onClickFAB() {
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = AddRealEstateFragment.newInstance();
                getParentFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .add(R.id.description_fragment, fragment, null)
                        .commit();
                MainActivity.hideViewPager();
            }
        });
    }

    //Make fab disapear if scroll down the RV
    private void onScrollRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0)
                    mFAB.hide();
                else if (dy < 0)
                    mFAB.show();
            }
        });
    }
}