package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.repository.ApplicationPreferencesRepo;
import com.openclassrooms.realestatemanager.repository.CriteriaRepo;

/**
 * Created by NIATEL Brice on 02/04/2021.
 */
public class MainActivityViewModel extends AndroidViewModel {

    private final CriteriaRepo mCriteriaRepo;
    private final ApplicationPreferencesRepo mApplicationPreferencesRepo;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mCriteriaRepo = ((MyApplication) application).getContainerDependencies().getCriteriaRepo();
        mApplicationPreferencesRepo = ((MyApplication) application).getContainerDependencies().getApplicationPreferencesRepo();
    }

    public boolean isCriteria() {
        return mCriteriaRepo.isCriteria();
    }

    public void deleteSharedPrefCriteria() {
        mApplicationPreferencesRepo.deleteSharedPrefsCriteria();

    }

    public String getSharedPrefItemDescription() {
        return mApplicationPreferencesRepo.getSharedPrefsFirstItemDescription();
    }
}
