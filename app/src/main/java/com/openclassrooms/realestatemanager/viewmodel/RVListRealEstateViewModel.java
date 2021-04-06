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

import java.util.List;

public class RVListRealEstateViewModel extends AndroidViewModel {
    private final RealEstateRepository mRealEstateRepository;
    private final CriteriaRepo mCriteriaRepo;

    public RVListRealEstateViewModel(@NonNull Application application) {
        super(application);
        mRealEstateRepository = ((MyApplication) application).getContainerDependencies().getRealEstateRepository();
        mCriteriaRepo = ((MyApplication) application).getContainerDependencies().getCriteriaRepo();
    }

    public LiveData<List<RealEstate>> getAllRealEstate() {
        return mRealEstateRepository.getAllRealEstate();
    }

    public List<RealEstate> filterRealEstates(Criteria criteria) {
        mCriteriaRepo.setCriteria(criteria);
        return mCriteriaRepo.filterAllParameters();
    }

    public List<RealEstate> getRealEstatesBackUp() {
        return mCriteriaRepo.getRealEstatesListBackUp();
    }

    public boolean isCriteria() {
        return mCriteriaRepo.isCriteria();
    }
}
