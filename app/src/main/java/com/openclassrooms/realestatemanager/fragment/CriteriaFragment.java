package com.openclassrooms.realestatemanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.utils.CriteriaReceiver;
import com.openclassrooms.realestatemanager.viewmodel.CriteriaViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CriteriaFragment extends Fragment {

    private static final String TAG = "CriteriaFragment";

    private static final int ROUND_MINIMAL = 0;
    private static final int ROUND_MAXIMAL = 1;

    public static final int PRICE = 10000;
    public static final int SURFACE = 10;

    public static final int MAX_PRICE = 500000;
    public static final int MAX_SURFACE = 500;
    public static final int MAX_POI = 40;

    private CriteriaViewModel mViewModel;

    private AutoCompleteTextView mTIType;

    private TextInputEditText mTIPriceMin, mTIPriceMax, mTISurfaceMin, mTISurfaceMax;
    private CheckBox mCheckBox;
    private TextInputEditText mTIRoom, mTIPoi;

    private RangeSlider mRSPrice, mRSSurface;
    private Slider mSPoi;

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

        mRSPrice = view.findViewById(R.id.range_slide_price);
        mRSSurface = view.findViewById(R.id.range_slider_surface);
        mSPoi = view.findViewById(R.id.slider_poi);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CriteriaViewModel.class);
        manageCriteria();
        linkSlider();
        linkEditText();
        setDropDownTextInput();
        setCriteriaSharedPref();
    }

    //Save criteria in sharedPref
    private void setCriteriaSharedPref() {
        Criteria criteria = mViewModel.getSharedPref();

        List<Float> listPrice = new ArrayList<>();
        List<Float> listSurface = new ArrayList<>();

        if (criteria.getType() != null) {
            Log.d(TAG, "setCriteriaSharedPref: getType");
            mTIType.setText(criteria.getType());
        }
        if (criteria.getMinPrice() != null) {
            Log.d(TAG, "setCriteriaSharedPref: getMinPrice");
            mTIPriceMin.setText(criteria.getMinPrice());
            listPrice.add(Float.valueOf(Objects.requireNonNull(mTIPriceMin.getText()).toString()));
        }
        if (criteria.getMaxPrice() != null) {
            Log.d(TAG, "setCriteriaSharedPref: getMaxPrice");
            mTIPriceMax.setText(criteria.getMaxPrice());
            listPrice.add(Float.valueOf(Objects.requireNonNull(mTIPriceMax.getText()).toString()));
            mRSPrice.setValues(listPrice);
        }
        if (criteria.getMinSurface() != null) {
            Log.d(TAG, "setCriteriaSharedPref: getMinSurface");
            mTISurfaceMin.setText(criteria.getMinSurface());
            listSurface.add(Float.valueOf(Objects.requireNonNull(mTISurfaceMin.getText()).toString()));
        }
        if (criteria.getMaxSurface() != null) {
            Log.d(TAG, "setCriteriaSharedPref: getMaxSurface");
            mTISurfaceMax.setText(criteria.getMaxSurface());
            listSurface.add(Float.valueOf(Objects.requireNonNull(mTISurfaceMax.getText()).toString()));
            mRSSurface.setValues(listSurface);
        }
        Log.d(TAG, "setCriteriaSharedPref: getAvailable");
        mCheckBox.setChecked(criteria.getAvailable());

        if (criteria.getRoomNumber() != null) {
            Log.d(TAG, "setCriteriaSharedPref: getRoomNumber");
            mTIRoom.setText(criteria.getRoomNumber());
        }
        if (criteria.getPoi() != null) {
            Log.d(TAG, "setCriteriaSharedPref: getPoi");
            mTIPoi.setText(criteria.getPoi());
            mSPoi.setValue(Float.parseFloat(Objects.requireNonNull(mTIPoi.getText()).toString()));
        }
    }

    //Get the criteria which the user select and apply it when he confirm
    private void manageCriteria() {
        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Criteria criteria = getCriteria();
                Log.d(TAG, "onClick: " + getCriteria().toString());

                mViewModel.setCriteria(criteria);

                //Send broadcast with the criteria object
                Intent intent = new Intent();
                intent.setAction(CriteriaReceiver.APPLY_CRITERIA);
                intent.putExtra("Criteria", getCriteria());
                requireContext().sendBroadcast(intent);

                //Save into shared pref
                mViewModel.saveSharedPrefCriteria(criteria);
            }
        });
    }

    //Get criteria from UI
    private Criteria getCriteria() {
        Criteria criteria = new Criteria();

        if (mTIType.getText() != null) {
            criteria.setType(mTIType.getText().toString());
        }
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

    //Link edit text with slider
    private void linkSlider() {
        //Price
        mRSPrice.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                Log.d(TAG, "linkSlider: price : " + mRSPrice.getValues());
                mTIPriceMin.setText(String.valueOf(Math.round(mRSPrice.getValues().get(0))));
                mTIPriceMax.setText(String.valueOf(Math.round(mRSPrice.getValues().get(1))));
            }
        });

        //Surface
        mRSSurface.addOnSliderTouchListener(new RangeSlider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull RangeSlider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull RangeSlider slider) {
                Log.d(TAG, "linkSlider: surface : " + mRSSurface.getValues());
                mTISurfaceMin.setText(String.valueOf(Math.round(mRSSurface.getValues().get(0))));
                mTISurfaceMax.setText(String.valueOf(Math.round(mRSSurface.getValues().get(1))));
            }
        });

        //Poi
        mSPoi.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                Log.d(TAG, "linkSlider: poi : " + (int) mSPoi.getValue());
                mTIPoi.setText(String.valueOf(Math.round(mSPoi.getValue())));
            }
        });
    }

    //Link edit text with slider
    private void linkEditText() {
        final Float[] minPrice = new Float[1];
        final Float[] maxPrice = new Float[1];

        final Float[] minSurface = new Float[1];
        final Float[] maxSurface = new Float[1];

        //Price
        mTIPriceMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maxPrice[0] = (!Objects.requireNonNull(mTIPriceMax.getText()).toString().isEmpty()) ? Float.parseFloat(mTIPriceMax.getText().toString()) : 500000f;

                Log.d(TAG, "onClick: minprice : " + maxPrice[0]);
                if (mTIPriceMin.getText() != null) {
                    mTIPriceMin.setText(String.valueOf(getTextInputResultRounded(mTIPriceMin.getText().toString(), ROUND_MINIMAL, PRICE)));

                    Log.d(TAG, "onClick: " + mTIPriceMin.getText().toString().trim());
                    if (Integer.parseInt(mTIPriceMin.getText().toString().trim()) <= MAX_PRICE) {
                        mRSPrice.setValues(new ArrayList<>(Arrays.asList(Float.valueOf(mTIPriceMin.getText().toString()), maxPrice[0])));
                    } else {
                        displayToast(MAX_PRICE);
                        mRSPrice.setValues(new ArrayList<>(Arrays.asList((float) MAX_PRICE, (float) MAX_PRICE)));

                        mTIPriceMin.setText(String.valueOf(MAX_PRICE));
                    }

                    mTIPriceMax.setText(String.valueOf(Math.round(maxPrice[0])));
                }
                Log.d(TAG, "linkSlider: price : " + mRSPrice.getValues());
            }
        });

        //Price
        mTIPriceMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minPrice[0] = (!Objects.requireNonNull(mTIPriceMin.getText()).toString().isEmpty()) ? Float.parseFloat(mTIPriceMin.getText().toString()) : 0f;

                Log.d(TAG, "onClick: minprice : " + minPrice[0]);
                if (mTIPriceMax.getText() != null) {
                    mTIPriceMax.setText(String.valueOf(getTextInputResultRounded(mTIPriceMax.getText().toString(), ROUND_MAXIMAL, PRICE)));

                    if (Integer.parseInt(mTIPriceMax.getText().toString()) <= MAX_PRICE) {
                        mRSPrice.setValues(new ArrayList<>(Arrays.asList(minPrice[0], Float.valueOf(mTIPriceMax.getText().toString()))));
                    } else {
                        displayToast(MAX_PRICE);
                        mRSPrice.setValues(new ArrayList<>(Arrays.asList(minPrice[0], (float) MAX_PRICE)));
                    }
                    mTIPriceMin.setText(String.valueOf(Math.round(minPrice[0])));
                }
                Log.d(TAG, "linkSlider: price : " + mRSPrice.getValues());
            }
        });

        //Surface
        mTISurfaceMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maxSurface[0] = (!Objects.requireNonNull(mTISurfaceMax.getText()).toString().isEmpty()) ? Float.parseFloat(mTISurfaceMax.getText().toString()) : 500f;

                Log.d(TAG, "onClick: minsurface : " + maxSurface[0]);
                if (mTISurfaceMin.getText() != null) {
                    mTISurfaceMin.setText(String.valueOf(getTextInputResultRounded(mTISurfaceMin.getText().toString(), ROUND_MINIMAL, SURFACE)));

                    if (Integer.parseInt(mTISurfaceMin.getText().toString().trim()) <= MAX_SURFACE) {
                        mRSSurface.setValues(new ArrayList<>(Arrays.asList(Float.valueOf(mTISurfaceMin.getText().toString()), maxSurface[0])));
                    } else {
                        displayToast(MAX_SURFACE);
                        mRSSurface.setValues(new ArrayList<>(Arrays.asList((float) MAX_SURFACE, (float) MAX_SURFACE)));
                        mTISurfaceMin.setText(String.valueOf(MAX_SURFACE));
                    }
                    mTISurfaceMax.setText(String.valueOf(Math.round(maxSurface[0])));
                }
                Log.d(TAG, "linkSlider: surface : " + mRSSurface.getValues());
            }
        });

        //Surface
        mTISurfaceMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minSurface[0] = (!Objects.requireNonNull(mTISurfaceMin.getText()).toString().isEmpty()) ? Float.parseFloat(mTISurfaceMin.getText().toString()) : 0f;

                Log.d(TAG, "onClick: maxsurface : " + minSurface[0]);
                if (mTISurfaceMax.getText() != null) {
                    mTISurfaceMax.setText(String.valueOf(getTextInputResultRounded(mTISurfaceMax.getText().toString(), ROUND_MAXIMAL, SURFACE)));

                    if (Integer.parseInt(mTISurfaceMax.getText().toString()) <= MAX_SURFACE) {
                        mRSSurface.setValues(new ArrayList<>(Arrays.asList(minSurface[0], Float.valueOf(mTISurfaceMax.getText().toString()))));
                    } else {
                        displayToast(MAX_SURFACE);
                        mRSSurface.setValues(new ArrayList<>(Arrays.asList(minSurface[0], (float) MAX_SURFACE)));
                    }
                    mTISurfaceMin.setText(String.valueOf(Math.round(minSurface[0])));
                }
                Log.d(TAG, "linkSlider: surface : " + mRSSurface.getValues());
            }
        });

        mTIPoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTIPoi.getText() != null) {
                    if (Integer.parseInt(mTIPoi.getText().toString()) <= 40) {
                        mSPoi.setValue(Float.parseFloat(mTIPoi.getText().toString()));
                    } else {
                        displayToast(MAX_POI);
                        mSPoi.setValue(MAX_POI);
                    }
                }
            }
        });
    }

    //Round number at least and at most
    private int getTextInputResultRounded(String s, int roundType, int roundNumber) {
        float result = 0;
        switch (roundType) {
            case ROUND_MINIMAL: {
                result = (float) (roundNumber * Math.floor(Float.parseFloat(s) / roundNumber));
                break;
            }
            case ROUND_MAXIMAL: {
                result = (float) (roundNumber * Math.ceil(Float.parseFloat(s) / roundNumber));
                break;
            }
        }
        int resultInt = (int) result;
        Log.d(TAG, "getTextInputResultRounded: " + resultInt);
        return resultInt;
    }

    //Display toast to warn the user about the maximal number
    private void displayToast(int criteria) {
        switch (criteria) {
            case MAX_PRICE: {
                Toast.makeText(requireContext(), "The maximum number of PRICE is : " + MAX_PRICE, Toast.LENGTH_SHORT).show();
                break;
            }
            case MAX_SURFACE: {
                Toast.makeText(requireContext(), "The maximum number of SURFACE is : " + MAX_SURFACE, Toast.LENGTH_SHORT).show();
                break;
            }
            case MAX_POI: {
                Toast.makeText(requireContext(), "The maximum number of POI is : " + MAX_POI, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    //Set text to choose in drop down text input
    private void setDropDownTextInput() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.dropdown_menu_popup_item, mViewModel.getItems());
        mTIType.setAdapter(adapter);
    }
}