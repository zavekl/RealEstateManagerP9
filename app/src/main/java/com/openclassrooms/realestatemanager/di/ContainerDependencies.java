package com.openclassrooms.realestatemanager.di;

import android.content.Context;
import android.util.Log;

import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;


/**
 * Created by <NIATEL Brice> on <16/04/2020>.
 */
public class ContainerDependencies {
    private static final String TAG = "ContainerDependencies";
    private final RealEstateRepository mRealEstateRepository;
    private final InternalFilesRepository mInternalFilesRepository;

    ContainerDependencies(Context context) {
        mRealEstateRepository = new RealEstateRepository(context);
        mInternalFilesRepository = new InternalFilesRepository(context);
    }

    public RealEstateRepository getRealEstateRepository() {
        Log.d(TAG, "getRealEstateRepository: ");
        return mRealEstateRepository;
    }

    public InternalFilesRepository getInternalFilesRepository() {
        return mInternalFilesRepository;
    }
}
