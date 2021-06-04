package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;

/**
 * Created by <NIATEL Brice> on <21/01/2021>.
 */
public class DescriptionRealEstateActivityViewModel extends AndroidViewModel {
    private final RealEstateRepository mRealEstateRepository;

    public DescriptionRealEstateActivityViewModel(@NonNull Application application) {
        super(application);
        mRealEstateRepository = new RealEstateRepository(application);
    }

    public LiveData<RealEstate> getRealestateById(long id) {
        return mRealEstateRepository.getRealEstateById(id);
    }

    public void updateRealEstate(RealEstate realEstate) {
        mRealEstateRepository.update(realEstate);
    }
}
