package com.openclassrooms.realestatemanager.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.viewmodel.CriteriaViewModel;

public class CriteriaFragment extends Fragment {

    private static final String TAG = "CriteriaFragment";

    private CriteriaViewModel mViewModel;

    private AutoCompleteTextView mTIType;

    private TextInputEditText mTIPriceMin, mTIPriceMax, mTISurfaceMin, mTISurfaceMax;
    private CheckBox mCheckBox;
    private TextInputEditText mTIRoom, mTIPoi;

    private MaterialButton mButtonConfirm;

    public static CriteriaFragment newInstance() {
        return new CriteriaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.criteria_fragment, container, false);

        mButtonConfirm = view.findViewById(R.id.b_confirm);
        mTIType = view.findViewById(R.id.ti_type_criteria);
        mTIPriceMin = view.findViewById(R.id.ti_price_criteria1);
        mTIPriceMax = view.findViewById(R.id.ti_price_criteria2);
        mTISurfaceMin = view.findViewById(R.id.ti_surface_criteria1);
        mTISurfaceMax = view.findViewById(R.id.ti_surface_criteria2);
        mCheckBox = view.findViewById(R.id.checkbox_criteria);
        mTIRoom = view.findViewById(R.id.ti_room_criteria);
        mTIPoi = view.findViewById(R.id.ti_poi_criteria);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CriteriaViewModel.class);
        manageCriteria();
    }

    //Get the criteria which the user select and apply it when he confirm
    private void manageCriteria() {
        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO set les criteres dans les fragments map + rv via Repo qui stoque les données, le fragment qui fait un get + if null --> rien
                Log.d(TAG, "onClick: " + getCriteria().toString());
            }
        });
    }

    //Get criteria from UI
    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setType(mTIType.getText().toString());
        if (mTIPriceMin.getText() != null) {
            criteria.setMinPrice(mTIPriceMin.getText().toString());
        }
        if (mTIPriceMax.getText() != null) {
            criteria.setMaxPrice(mTIPriceMax.getText().toString());
        }
        if (mTISurfaceMin.getText() != null) {
            criteria.setMinSurface(mTISurfaceMin.getText().toString());
        }
        if (mTISurfaceMax.getText() != null) {
            criteria.setMaxSurface(mTISurfaceMax.getText().toString());
        }
        if (mTIRoom.getText() != null) {
            criteria.setRoomNumber(mTIRoom.getText().toString());
        }

        criteria.setAvailable(mCheckBox.isChecked());

        if (mTIPoi.getText() != null) {
            criteria.setPoi(mTIPoi.getText().toString());
        }

        return criteria;
    }
}