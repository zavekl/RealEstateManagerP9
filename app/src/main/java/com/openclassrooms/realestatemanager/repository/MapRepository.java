package com.openclassrooms.realestatemanager.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by <NIATEL BRICE> on <01/05/2020>.
 */
public class MapRepository {
    private static final int UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    private static final int FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private final FusedLocationProviderClient fusedLocationProviderClient;

    public MapRepository(Context context) {
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    }

    //Start location update
    @SuppressLint("MissingPermission")
    public void startLocationUpdates(LocationCallback locationCallback) {
        fusedLocationProviderClient.requestLocationUpdates(createLocationRequest(), locationCallback, Looper.getMainLooper());
    }

    //Stop location update
    public void stopLocationUpdates(LocationCallback locationCallback) {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    //Create location request
    private LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = LocationRequest.create();

        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        return mLocationRequest;
    }
}
