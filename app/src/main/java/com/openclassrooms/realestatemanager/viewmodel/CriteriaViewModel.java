package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.repository.CriteriaRepo;

import java.util.Arrays;
import java.util.List;

public class CriteriaViewModel extends AndroidViewModel {

    private final CriteriaRepo mCriteriaRepo;

    public CriteriaViewModel(@NonNull Application application) {
        super(application);
        mCriteriaRepo = ((MyApplication) application).getContainerDependencies().getCriteriaRepo();
    }

    public List<String> getItems() {
        return Arrays.asList("House", "Castle", "Mansion", "Duplex", "Apartment");
    }

    public void setCriteriaRepo(Criteria criteria) {
        mCriteriaRepo.setCriteria(criteria);
    }
}