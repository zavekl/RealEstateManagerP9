package com.openclassrooms.realestatemanager.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.bdd.RealEstateDao;
import com.openclassrooms.realestatemanager.bdd.RealEstateDatabase;
import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.List;

/**
 * Created by <NIATEL Brice> on <19/01/2021>.
 */
public class RealEstateRepository {
    private RealEstateDao mRealEstateDao;
    private LiveData<List<RealEstate>> mAllRealEstates;

    public RealEstateRepository(Application application) {
        RealEstateDatabase database = RealEstateDatabase.getInstance(application);
        mRealEstateDao = database.realEstateDao();
        mAllRealEstates = mRealEstateDao.getRealEstates();
    }

    public void insert(RealEstate realEstate) {
        new InsertRealEstateAsyncTask(mRealEstateDao).execute(realEstate);
    }

    public void update(RealEstate realEstate) {
        new UpdateRealEstateAsyncTask(mRealEstateDao).execute(realEstate);
    }

    public void delete(RealEstate realEstate) {
        new DeleteRealEstateAsyncTask(mRealEstateDao).execute(realEstate);
    }

    public LiveData<RealEstate> getRealEstateById(long id) {
        return mRealEstateDao.getRealEstateById(id);
    }

    public LiveData<List<RealEstate>> getAllRealEstates() {
        return mAllRealEstates;
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

    private static class DeleteRealEstateAsyncTask extends AsyncTask<RealEstate, Void, Void> {
        private final RealEstateDao mRealEstateDao;

        private DeleteRealEstateAsyncTask(RealEstateDao realEstateDao) {
            this.mRealEstateDao = realEstateDao;
        }

        @Override
        protected Void doInBackground(RealEstate... realEstates) {
            mRealEstateDao.deleteRealEstate(realEstates[0]);
            return null;
        }
    }
}
