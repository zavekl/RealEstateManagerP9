package com.openclassrooms.realestatemanager.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.realestatemanager.Constants;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.viewmodel.SimulatorRealEstateLoanViewModel;

public class SimulatorRealEstateLoanFragment extends Fragment {

    private static final String TAG = "SimulatorFragment";

    private SimulatorRealEstateLoanViewModel mViewModel;

    private TextInputEditText mMoneyContribution;
    private TextInputEditText mNumberYears;
    private TextInputEditText mNumberRate;

    private Slider mYearsSlider;
    private Slider mRateSlider;

    private TextView mResultMonth;
    private TextView mResultCreditCost;

    private String mPrice;


    public static SimulatorRealEstateLoanFragment newInstance() {
        return new SimulatorRealEstateLoanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simulator_fragment, container, false);

        mYearsSlider = view.findViewById(R.id.slider_years);
        mRateSlider = view.findViewById(R.id.slider_rate);
        mRateSlider.setStepSize(0.04999999f); //Fix step size bug +-0.01 of 0.05

        mMoneyContribution = view.findViewById(R.id.ti_price);
        mNumberYears = view.findViewById(R.id.ti_years);
        mNumberRate = view.findViewById(R.id.ti_rate);

        mResultMonth = view.findViewById(R.id.tv_result2_simulator);
        mResultCreditCost = view.findViewById(R.id.tv_result4_simulator);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SimulatorRealEstateLoanViewModel.class);

        linkSlider();
        linkEditText();

        //Get the price which was shared with the fragment
        if (getArguments() != null) {
            Log.d(TAG, "onActivityCreated: get price");
            mPrice = String.valueOf(requireArguments().getInt(Constants.BUNDLE_ID2));
        }
    }

    //Link slider with edit text
    private void linkSlider() {
        mYearsSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                Log.d(TAG, "linkSlider: years : " + slider.getValue());
                mNumberYears.setText(String.valueOf((int) slider.getValue()));
                calculateResults();
            }
        });

        mRateSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                Log.d(TAG, "linkSlider: surface : " + slider.getValue());
                mNumberRate.setText(String.valueOf((getRound(slider.getValue()))));
                calculateResults();
            }
        });
    }

    //Link edit text with slider
    private void linkEditText() {
        mNumberYears.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: ");
                if (mNumberYears.getText() != null && s.length() >= 1) {
                    if (Float.parseFloat(s.toString()) < 30.0f) {
                        Log.d(TAG, "afterTextChanged: mNumberYears");
                        mYearsSlider.setValue(Float.parseFloat(s.toString()));
                        calculateResults();
                    }
                }
            }
        });

        mNumberRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mNumberRate.getText() != null && s.length() >= 1) {
                    if (Float.parseFloat(s.toString()) < 5.0f) {
                        mRateSlider.setValue(Float.parseFloat(s.toString()));
                        calculateResults();
                    }
                }
            }
        });

        mMoneyContribution.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mMoneyContribution.getText() != null && s.length() >= 1) {
                    if (Integer.parseInt(mPrice) > Integer.parseInt(s.toString())) {
                        mPrice = String.valueOf(Integer.parseInt(mPrice) - Integer.parseInt(s.toString()));
                        calculateResults();
                    } else {
                        mMoneyContribution.setError(getString(R.string.tie_money_contribution_error));
                    }
                }
            }
        });
    }

    private void calculateResults() {
        if (mNumberYears.getText() != null && mNumberRate.getText() != null) {
            if (!mNumberYears.getText().toString().equals("") && !mNumberRate.getText().toString().equals("")) {
                //Set values
                mViewModel.setRealEstatePrice(String.valueOf(mPrice));
                mViewModel.setTimeCreditYear(mNumberYears.getText().toString());
                mViewModel.setInterestRate(mNumberRate.getText().toString());

                //Calculate results
                StringBuilder s1 = new StringBuilder("  " + mViewModel.calculateRealEstateLoan() + "€  ");
                StringBuilder s2;
                if (mViewModel.calculateCostOfCredit() > 0) {
                    s2 = new StringBuilder("  " + mViewModel.calculateCostOfCredit() + "€  ");
                } else {
                    s2 = new StringBuilder("  0€ ");
                }

                mResultMonth.setText(s1);
                mResultCreditCost.setText(s2);
            }
        }
    }

    //To fix the bug of stepsize of slider
    private float getRound(float f) {
        return Math.round((f * 100f)) / 100f;
    }
}