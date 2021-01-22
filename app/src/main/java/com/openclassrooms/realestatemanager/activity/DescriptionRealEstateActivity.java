package com.openclassrooms.realestatemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.viewmodel.DescriptionRealEstateActivityViewModel;

/**
 * Created by <NIATEL Brice> on <29/12/2020>.
 */
public class DescriptionRealEstateActivity extends AppCompatActivity {
    private static final String TAG = "DescRealEstateActivity";
    private TextView mDescription;
    private DescriptionRealEstateActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);
        mDescription = findViewById(R.id.tv_description);
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).
                get(DescriptionRealEstateActivityViewModel.class);

        setData();
    }

    private void setData() {
        long data = getIntent().getExtras().getLong("id", -1);
        Log.d(TAG, "setData: " + data);
        if (data != -1) {
            mViewModel.getRealestateById(data).observe(this, new Observer<RealEstate>() {
                @Override
                public void onChanged(RealEstate realEstate) {
                    //TODO Faire le reste
                    mDescription.setText(realEstate.getDescription());
                }
            });
        }
    }
}
