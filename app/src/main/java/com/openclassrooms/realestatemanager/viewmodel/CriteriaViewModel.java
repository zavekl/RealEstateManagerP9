package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.CriteriaRepo;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;

import java.util.Arrays;
import java.util.List;

public class CriteriaViewModel extends AndroidViewModel {

    private final CriteriaRepo mCriteriaRepo;
    private final RealEstateRepository mRealEstateRepository;

    public CriteriaViewModel(@NonNull Application application) {
        super(application);
        mCriteriaRepo = ((MyApplication) application).getContainerDependencies().getCriteriaRepo();
        mRealEstateRepository = ((MyApplication) application).getContainerDependencies().getRealEstateRepository();
    }

    public List<String> getItems() {
        return Arrays.asList("House", "Castle", "Mansion", "Duplex", "Apartment");
    }

    public void setCriteriaRepo(Criteria criteria) {
        mCriteriaRepo.setCriteria(criteria);
    }

    public LiveData<List<RealEstate>> getAllRealEstate() {
        return mRealEstateRepository.getAllRealEstate();
    }
}