package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;
import android.location.LocationListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.location.LocationCallback;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.CriteriaRepo;
import com.openclassrooms.realestatemanager.repository.MapRepository;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;

import java.util.List;

public class MapFragmentViewModel extends AndroidViewModel {
    private static final String TAG = "MapFragmentViewModel";

    private final MapRepository mMapRepository;
    private final LiveData<List<RealEstate>> mAllRealestateAddress;
    private final CriteriaRepo mCriteriaRepo;

    public MapFragmentViewModel(@NonNull Application application) {
        super(application);
        mMapRepository = ((MyApplication) application).getContainerDependencies().getMapRepository();
        RealEstateRepository realEstateRepository = ((MyApplication) application).getContainerDependencies().getRealEstateRepository();
        mAllRealestateAddress = realEstateRepository.getAllRealEstate();
        mCriteriaRepo = ((MyApplication) application).getContainerDependencies().getCriteriaRepo();
    }

    public void startLocationUpdates(LocationCallback locationCallback) {
        mMapRepository.startLocationUpdates(locationCallback);
    }

    public void stopLocationUpdates(LocationCallback locationCallback) {
        mMapRepository.stopLocationUpdates(locationCallback);
    }

    public void requestGPSUpdate(LocationListener locationListener) {
        mMapRepository.requestGPSUpdate(locationListener);
    }

    public LiveData<List<RealEstate>> getAllRealEstate() {
        return mAllRealestateAddress;
    }

    public void setRealEstateList(List<RealEstate> l) {
        mCriteriaRepo.setRealEstatesList(l);
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