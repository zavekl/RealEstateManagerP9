package com.openclassrooms.realestatemanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.activity.MainActivity;
import com.openclassrooms.realestatemanager.adapter.DescriptionAdapter;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.viewmodel.DescriptionRealEstateActivityViewModel;

import static com.openclassrooms.realestatemanager.activity.MainActivity.hideCriteriaButton;
import static com.openclassrooms.realestatemanager.adapter.ListRealEstateRVAdapter.BUNDLE_ID_DESCRIPTION;

public class DescriptionRealEstateFragment extends Fragment {
    private static final String TAG = "DescRealEstateFragment";
    public static final String BUNDLE_PRICE_SIMULATOR = "BUNDLE_PRICE";


    private int mPrice;

    private RecyclerView mRecyclerView;
    private TextView mDescription;
    private TextView mLocation1, mLocation2, mLocation3, mNumberPoi;
    private TextView mSurface, mNumberRoom, mNumberBedroom, mNumberBathroom, mTVPrice;

    private ImageButton mSimulatorButton;

    public static DescriptionRealEstateFragment newInstance() {
        return new DescriptionRealEstateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragment, container, false);

        mRecyclerView = view.findViewById(R.id.rv_description);

        mDescription = view.findViewById(R.id.tv_description);

        mLocation1 = view.findViewById(R.id.tv_location1);
        mLocation2 = view.findViewById(R.id.tv_location2);
        mLocation3 = view.findViewById(R.id.tv_location3);

        mSurface = view.findViewById(R.id.tv_surface);
        mNumberRoom = view.findViewById(R.id.tv_room);
        mNumberBedroom = view.findViewById(R.id.tv_bedroom);
        mNumberBathroom = view.findViewById(R.id.tv_bathroom);
        mNumberPoi = view.findViewById(R.id.tv_poi);
        mTVPrice = view.findViewById(R.id.tv_price);

        mSimulatorButton = view.findViewById(R.id.simulator);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DescriptionRealEstateActivityViewModel mViewModel = new ViewModelProvider(this).get(DescriptionRealEstateActivityViewModel.class);

        hideCriteriaButton();

        final DescriptionAdapter adapter = new DescriptionAdapter(requireActivity());
        mRecyclerView.setAdapter(adapter);

        if (getArguments() != null) {
            long id = requireArguments().getLong(BUNDLE_ID_DESCRIPTION);
            Log.d(TAG, "onActivityCreated: id = " + id);

            mViewModel.getRealestateById(id).observe((LifecycleOwner) requireContext(), new Observer<RealEstate>() {
                @Override
                public void onChanged(RealEstate realEstate) {
                    mPrice = realEstate.getPrice();

                    adapter.setItems(realEstate.getListPathImage());

                    mDescription.setText(realEstate.getDescription());

                    mLocation1.setText(realEstate.getAddress().getNumberStreet().trim());
                    mLocation2.setText(realEstate.getAddress().getPostalCode().trim());
                    mLocation3.setText(realEstate.getAddress().getTown().trim());
                    Log.d(TAG, "onChanged: latlng : " + realEstate.getAddress().getLat() + "/" + realEstate.getAddress().getLng());

                    mSurface.setText(new StringBuilder(realEstate.getSurface() + getString(R.string.simple_space) + getString(R.string.m2)));
                    mTVPrice.setText(new StringBuilder(realEstate.getPrice() + getString(R.string.simple_space) + getString(R.string.dollar)));
                    mNumberRoom.setText(String.valueOf(realEstate.getPieceNumber()));
                    mNumberBedroom.setText(String.valueOf(realEstate.getBedroomNumber()));
                    mNumberBathroom.setText(String.valueOf(realEstate.getBathroomNumber()));
                    mNumberPoi.setText(String.valueOf(realEstate.getPointOfInterest()));

                }
            });
        } else {
            Log.d(TAG, "onActivityCreated: getArguments() = null");
        }

        setOnClickSimulatorButton();
    }

    private void setOnClickSimulatorButton() {
        mSimulatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: simulator button");
                Fragment fragment = SimulatorRealEstateLoanFragment.newInstance();
                Bundle bundle = new Bundle();
                bundle.putInt(BUNDLE_PRICE_SIMULATOR, mPrice);

                fragment.setArguments(bundle);

                getParentFragmentManager().beginTransaction()
                        .add(R.id.description_fragment, fragment)
                        .commit();

                MainActivity.displaySearchFragment();
            }
        });
    }
}