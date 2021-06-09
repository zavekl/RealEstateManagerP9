package com.openclassrooms.realestatemanager.fragment;

import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.utils.CriteriaReceiver;
import com.openclassrooms.realestatemanager.utils.ToolbarReceiver;
import com.openclassrooms.realestatemanager.utils.VerticalSpaceItemDecoration;
import com.openclassrooms.realestatemanager.viewmodel.RVListRealEstateViewModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.http.HEAD;

public class RVListRealEstateFragment extends Fragment implements CriteriaReceiver.ICustomListener, ToolbarReceiver.ICustomListener {

    private static final String TAG = "RVLREstateFragment";

    private ListRealEstateRVAdapter mAdapter;

    private CriteriaReceiver mReceiverCriteria;
    private ToolbarReceiver mReceiverToolbar;

    private FloatingActionButton mFAB;

    private RVListRealEstateViewModel mViewModel;

    private final List<RealEstate> mRealEstates = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rv_real_estate_fragment, container, false);

        mFAB = view.findViewById(R.id.rv_fab);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RVListRealEstateViewModel.class);

        //Set item inside RV
        setItemAdapter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        final RecyclerView recyclerView = view.findViewById(R.id.rv_real_estate);
        mAdapter = new ListRealEstateRVAdapter(requireActivity(), this);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(15));
        recyclerView.setAdapter(mAdapter);

        onScrollRecyclerView(recyclerView);
        onClickFAB();
        setItem();
    }

    //On click of FAB run AddRealEstateFragment
    private void onClickFAB() {
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.displayDescriptionFragment();
                Fragment fragment = AddRealEstateFragment.newInstance();
                getParentFragmentManager().beginTransaction()
                        .add(R.id.description_fragment, fragment, null)
                        .commit();
                if (!MainActivity.mTabletMode) {
                    Log.d(TAG, "onClick: phone mode");
                    MainActivity.hideViewPager();
                }
            }
        });
    }

    //Get realestates items and add it in RV
    private void setItemAdapter() {
        mViewModel.getAllRealEstate().observe((LifecycleOwner) requireContext(), new Observer<List<RealEstate>>() {
            @Override
            public void onChanged(List<RealEstate> realEstates) {
                mRealEstates.clear();
                mRealEstates.addAll(realEstates);
                Log.d(TAG, "onChanged: setItemsAdapter : " + mRealEstates);
                
                setItem();
            }
        });
    }

    public void setItem() {
        Log.d("MainActivity", "setItem: ");
        mAdapter.setItems(mRealEstates);
    }

    //Make fab disappear if scroll down the RV
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

    //When broadcast is detected it filter item or set backup list
    @Override
    public void applyCriteria(Criteria criteria) {
        if (mViewModel.isCriteria()) {
            Log.d(TAG, "applyCriteria: filtered");
            mAdapter.setItems(mViewModel.filterRealEstates(criteria));
        } else {
            Log.d(TAG, "applyCriteria: empty filter get backup and set it");
            mAdapter.setItems(mViewModel.getRealEstatesBackUp());
        }
    }

    //When broadcast is detected it set backup list
    @Override
    public void applyResetCriteria() {
        Log.d(TAG, "applyResetCriteria: " + mViewModel.getRealEstatesBackUp().size());
        mAdapter.setItems(mViewModel.getRealEstatesBackUp());
    }

    //Initialize receiver for broadcast
    private void initReceiver() {
        Log.d(TAG, "initReceiver: start");
        Log.d(TAG, "initReceiver: CriteriaReceiver");
        mReceiverCriteria = new CriteriaReceiver();
        mReceiverCriteria.setCallback(this);
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(CriteriaReceiver.APPLY_CRITERIA);
        requireContext().registerReceiver(mReceiverCriteria, intentFilter1);

        Log.d(TAG, "initReceiver: ToolbarReceiver");
        mReceiverToolbar = new ToolbarReceiver();
        mReceiverToolbar.setCallback(this);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(ToolbarReceiver.APPLY_RESET_CRITERIA);
        requireContext().registerReceiver(mReceiverToolbar, intentFilter2);

        Log.d(TAG, "initReceiver: end");
    }

    @Override
    public void onResume() {
        super.onResume();
        initReceiver();
        Log.d(TAG, "onResume: ");

        if (MainActivity.mTabletMode) {
            new UpdatePhotoFit(this).execute();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        requireContext().unregisterReceiver(mReceiverCriteria);
        requireContext().unregisterReceiver(mReceiverToolbar);
        Log.d(TAG, "onPause: ");
    }

    private static class UpdatePhotoFit extends AsyncTask<Void, Void, Void> {
        private final WeakReference<RVListRealEstateFragment> activityReference;

        UpdatePhotoFit(final RVListRealEstateFragment mainActivity) {
            activityReference = new WeakReference<>(mainActivity);
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            final RVListRealEstateFragment activity = activityReference.get();
            if (activity != null) {
                try {
                    Thread.sleep(10);
                    activity.requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.setItem();
                        }
                    });
                } catch (final InterruptedException e) {
                    Log.e(TAG, "doInBackground: ", e);
                    Thread.currentThread().interrupt();
                }
            }
            return null;
        }
    }
}