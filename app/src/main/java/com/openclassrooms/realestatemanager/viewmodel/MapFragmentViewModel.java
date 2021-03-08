package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;
import android.location.LocationListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.location.LocationCallback;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.MapRepository;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;

import java.util.List;

public class MapFragmentViewModel extends AndroidViewModel {
    private final MapRepository mMapRepository;
    private final LiveData<List<RealEstate>> mAllRealestates;


    public MapFragmentViewModel(@NonNull Application application) {
        super(application);
        mMapRepository = ((MyApplication) application).getContainerDependencies().getMapRepository();
        RealEstateRepository realEstateRepository = ((MyApplication) application).getContainerDependencies().getRealEstateRepository();
        mAllRealestates = realEstateRepository.getAllRealEstate();
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
        return mAllRealestates;
    }
}