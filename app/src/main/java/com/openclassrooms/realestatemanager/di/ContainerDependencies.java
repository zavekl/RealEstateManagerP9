package com.openclassrooms.realestatemanager.di;

import android.content.Context;

import com.openclassrooms.realestatemanager.repository.ApplicationPreferencesRepo;
import com.openclassrooms.realestatemanager.repository.CriteriaRepo;
import com.openclassrooms.realestatemanager.repository.EstateLoanSimulatorRepo;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;
import com.openclassrooms.realestatemanager.repository.MapRepository;
import com.openclassrooms.realestatemanager.repository.NotificationRepo;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;
import com.openclassrooms.realestatemanager.repository.RetrofitRepository;


/**
 * Created by <NIATEL Brice> on <16/04/2020>.
 */
public class ContainerDependencies {
    private final RealEstateRepository mRealEstateRepository;
    private final InternalFilesRepository mInternalFilesRepository;
    private final MapRepository mMapRepository;
    private final RetrofitRepository mRetrofitRepository;
    private final CriteriaRepo mCriteriaRepo;
    private final NotificationRepo mNotificationRepo;
    private final ApplicationPreferencesRepo mApplicationPreferencesRepo;
    private final EstateLoanSimulatorRepo mEstateLoanSimulatorRepo;

    ContainerDependencies(Context context) {
        mRealEstateRepository = new RealEstateRepository(context);
        mInternalFilesRepository = new InternalFilesRepository(context);
        mMapRepository = new MapRepository(context);
        mRetrofitRepository = new RetrofitRepository();
        mCriteriaRepo = new CriteriaRepo();
        mNotificationRepo = new NotificationRepo(context);
        mApplicationPreferencesRepo = new ApplicationPreferencesRepo(context);
        mEstateLoanSimulatorRepo = new EstateLoanSimulatorRepo();
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

    public RetrofitRepository getRetrofitRepository() {
        return mRetrofitRepository;
    }

    public CriteriaRepo getCriteriaRepo() {
        return mCriteriaRepo;
    }

    public NotificationRepo getNotificationRepo() {
        return mNotificationRepo;
    }

    public ApplicationPreferencesRepo getApplicationPreferencesRepo() {
        return mApplicationPreferencesRepo;
    }

    public EstateLoanSimulatorRepo getEstateLoanSimulatorRepo() {
        return mEstateLoanSimulatorRepo;
    }
}
