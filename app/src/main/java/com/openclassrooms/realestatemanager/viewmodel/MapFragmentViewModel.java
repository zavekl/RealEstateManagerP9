package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;
import android.location.LocationListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.location.LocationCallback;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.repository.MapRepository;

public class MapFragmentViewModel extends AndroidViewModel {
    private final MapRepository mMapRepository;

    public MapFragmentViewModel(@NonNull Application application) {
        super(application);
        mMapRepository = ((MyApplication) application).getContainerDependencies().getMapRepository();
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
}