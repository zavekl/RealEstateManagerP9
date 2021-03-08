package com.openclassrooms.realestatemanager.di;

import android.content.Context;

import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;
import com.openclassrooms.realestatemanager.repository.MapRepository;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;


/**
 * Created by <NIATEL Brice> on <16/04/2020>.
 */
public class ContainerDependencies {
    private final RealEstateRepository mRealEstateRepository;
    private final InternalFilesRepository mInternalFilesRepository;
    private  final MapRepository mMapRepository;

    ContainerDependencies(Context context) {
        mRealEstateRepository = new RealEstateRepository(context);
        mInternalFilesRepository = new InternalFilesRepository(context);
        mMapRepository = new MapRepository(context);
    }

    public RealEstateRepository getRealEstateRepository() {
        return mRealEstateRepository;
    }

    public InternalFilesRepository getInternalFilesRepository() {
        return mInternalFilesRepository;
    }

    public MapRepository getMapRepository() {
        return mMapRepository;
    }
}
