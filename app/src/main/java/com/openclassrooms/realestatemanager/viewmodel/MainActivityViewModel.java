package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.repository.CriteriaRepo;

/**
 * Created by NIATEL Brice on 02/04/2021.
 */
public class MainActivityViewModel extends AndroidViewModel {

    private final CriteriaRepo mCriteriaRepo;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mCriteriaRepo = ((MyApplication) application).getContainerDependencies().getCriteriaRepo();
    }

    public boolean isCriteria() {
        return mCriteriaRepo.isCriteria();
    }
}
