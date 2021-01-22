package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;

import java.util.List;

public class RVListRealEstateViewModel extends AndroidViewModel {
    private final RealEstateRepository realEstateRepository;
    private LiveData<List<RealEstate>> mAllRealestates;

    public RVListRealEstateViewModel(@NonNull Application application) {
        super(application);
        realEstateRepository = new RealEstateRepository(application);
        mAllRealestates = realEstateRepository.getAllRealEstates();
    }

    public void insert(RealEstate realEstate) {
        realEstateRepository.insert(realEstate);
    }

    public void update(RealEstate realEstate) {
        realEstateRepository.update(realEstate);
    }

    public void delete(RealEstate realEstate) {
        realEstateRepository.delete(realEstate);
    }

    public LiveData<List<RealEstate>> getAllRealestates() {
        return mAllRealestates;
    }
}