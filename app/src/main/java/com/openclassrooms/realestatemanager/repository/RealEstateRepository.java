package com.openclassrooms.realestatemanager.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.bdd.RealEstateDao;
import com.openclassrooms.realestatemanager.bdd.RealEstateDatabase;
import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.List;

/**
 * Created by <NIATEL Brice> on <19/01/2021>.
 */
public class RealEstateRepository {
    private static final String TAG = "RealEstateRepository";
    private final RealEstateDao mRealEstateDao;
    private final LiveData<List<RealEstate>> mAllIdRealEstates;

    public RealEstateRepository(Context context) {
        Log.d(TAG, "RealEstateRepository: ");
        RealEstateDatabase database = RealEstateDatabase.getInstance(context);
        mRealEstateDao = database.realEstateDao();
        mAllIdRealEstates = mRealEstateDao.getAllRealEstate();
    }

    //Insert in DB
    public void insert(RealEstate realEstate) {
        Log.d(TAG, "insert: " + realEstate);
        new InsertRealEstateAsyncTask(mRealEstateDao).execute(realEstate);
    }

    //Update an item in DB
    public void update(RealEstate realEstate) {
        Log.d(TAG, "update: ");
        new UpdateRealEstateAsyncTask(mRealEstateDao).execute(realEstate);
    }

    //Get all the DB
    public LiveData<List<RealEstate>> getAllRealEstate() {
        Log.d(TAG, "getAllIdRealEstate: ");
        return mAllIdRealEstates;
    }

    //Get an item in DB
    public LiveData<RealEstate> getRealEstateById(long id) {
        Log.d(TAG, "getAllIdRealEstate: ");
        return mRealEstateDao.getRealEstateById(id);
    }

    private static class InsertRealEstateAsyncTask extends AsyncTask<RealEstate, Void, Void> {
        private final RealEstateDao mRealEstateDao;

        private InsertRealEstateAsyncTask(RealEstateDao realEstateDao) {
            this.mRealEstateDao = realEstateDao;
        }

        @Override
        protected Void doInBackground(RealEstate... realEstates) {
            mRealEstateDao.insertRealEstate(realEstates[0]);
            return null;
        }
    }

    private static class UpdateRealEstateAsyncTask extends AsyncTask<RealEstate, Void, Void> {
        private final RealEstateDao mRealEstateDao;

        private UpdateRealEstateAsyncTask(RealEstateDao realEstateDao) {
            this.mRealEstateDao = realEstateDao;
        }

        @Override
        protected Void doInBackground(RealEstate... realEstates) {
            mRealEstateDao.updateRealEstate(realEstates[0]);
            return null;
        }
    }
}
