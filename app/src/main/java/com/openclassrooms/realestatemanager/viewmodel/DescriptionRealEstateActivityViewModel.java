package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;

import java.util.List;

/**
 * Created by <NIATEL Brice> on <21/01/2021>.
 */
public class DescriptionRealEstateActivityViewModel extends AndroidViewModel {
    private final RealEstateRepository realEstateRepository;

    public DescriptionRealEstateActivityViewModel(@NonNull Application application) {
        super(application);
        realEstateRepository = new RealEstateRepository(application);
    }

    public LiveData<RealEstate> getRealestateById(long id) {
        return realEstateRepository.getRealEstateById(id);
    }
}
