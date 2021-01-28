package com.openclassrooms.realestatemanager.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.openclassrooms.realestatemanager.Constants;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.viewmodel.DescriptionRealEstateActivityViewModel;

/**
 * Created by <NIATEL Brice> on <29/12/2020>.
 */
public class DescriptionRealEstateActivity extends AppCompatActivity {
    private static final String TAG = "DescRealEstateActivity";

    private ImageView mImage;
    private TextView mDescription;
    private TextView mLocation1, mLocation2, mLocation3;
    private TextView mSurface, mNumberRoom, mNumberBedroom, mNumberBathroom;

    private DescriptionRealEstateActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);

        mImage = findViewById(R.id.iv_description);

        mDescription = findViewById(R.id.tv_description);

        mLocation1 = findViewById(R.id.tv_location1);
        mLocation2 = findViewById(R.id.tv_location2);
        mLocation3 = findViewById(R.id.tv_location3);

        mSurface = findViewById(R.id.tv_surface);
        mNumberRoom = findViewById(R.id.tv_room);
        mNumberBedroom = findViewById(R.id.tv_bedroom);
        mNumberBathroom = findViewById(R.id.tv_bathroom);


        mViewModel = new ViewModelProvider((ViewModelStoreOwner) this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).
                get(DescriptionRealEstateActivityViewModel.class);

        setData();
    }

    private void setData() {
        long data = getIntent().getExtras().getLong(Constants.INTENT_ID, -1);
        Log.d(TAG, "setData: " + data);
        if (data != -1) {
            mViewModel.getRealestateById(data).observe(this, new Observer<RealEstate>() {
                @Override
                public void onChanged(RealEstate realEstate) {
                    //TODO Point d'intéret à faire
                    mImage.setImageBitmap(mViewModel.getBitmap(realEstate.getImage()));

                    mDescription.setText(realEstate.getDescription());

                    mLocation1.setText(new StringBuilder(realEstate.getAddress().getNumber() + realEstate.getAddress().getStreet()));
                    mLocation2.setText(realEstate.getAddress().getPostalCode());
                    mLocation3.setText(realEstate.getAddress().getTown());

                    mSurface.setText(new StringBuilder(realEstate.getSurface() + " m2"));
                    mNumberRoom.setText(String.valueOf(realEstate.getPieceNumber()));
                    mNumberBedroom.setText(String.valueOf(realEstate.getBedroomNumber()));
                    mNumberBathroom.setText(String.valueOf(realEstate.getBathroomNumber()));
                }
            });
        }
    }
}
