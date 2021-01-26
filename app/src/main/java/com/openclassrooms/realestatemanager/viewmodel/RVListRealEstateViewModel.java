package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;

import java.util.List;

public class RVListRealEstateViewModel extends AndroidViewModel {
    private final RealEstateRepository mRealEstateRepository;
    private LiveData<List<Long>> mAllRealestates;

    public RVListRealEstateViewModel(@NonNull Application application) {
        super(application);
        mRealEstateRepository = ((MyApplication) application).getContainerDependencies().getRealEstateRepository();
        mAllRealestates = mRealEstateRepository.getAllIdRealEstate();
    }

    public void insert(RealEstate realEstate) {
        mRealEstateRepository.insert(realEstate);
    }

    public void update(RealEstate realEstate) {
        mRealEstateRepository.update(realEstate);
    }

    public void delete(RealEstate realEstate) {
        mRealEstateRepository.delete(realEstate);
    }

    public LiveData<List<Long>> getAllIdRealEstate() {
        return mAllRealestates;
    }
}
