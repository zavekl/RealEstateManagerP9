package com.openclassrooms.realestatemanager.bdd;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.openclassrooms.realestatemanager.model.Address;
import com.openclassrooms.realestatemanager.model.Converters;
import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.Collections;

/**
 * Created by <NIATEL Brice> on <19/01/2021>.
 */
@Database(entities = {RealEstate.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RealEstateDatabase extends RoomDatabase {
    private static final String TAG = "RealEstateDatabase";
    private static RealEstateDatabase instance;

    public abstract RealEstateDao realEstateDao();

    public static synchronized RealEstateDatabase getInstance(Context context) {
        if (instance == null) {
            Log.d(TAG, "getInstance: null so create it : ");
            instance = Room.databaseBuilder(context.getApplicationContext(), RealEstateDatabase.class, "real_estate_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        Log.d(TAG, "getInstance: end of getInstance");
        return instance;
    }

    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d(TAG, "onCreate: ");
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final RealEstateDao mRealEstateDao;

        private PopulateDbAsyncTask(RealEstateDatabase db) {
            mRealEstateDao = db.realEstateDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mRealEstateDao.insertRealEstate(new RealEstate("House", 120000, "150", 12, 4,
                    2, "Description1", new Address("1 Rue des fleurs", "96512", "NewYork", "47.499088", "-0.582984"),
                    "3", true, Collections.singletonList("creationDb1"), "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 1");

            mRealEstateDao.insertRealEstate(new RealEstate("House", 210000, "250", 16, 4,
                    2, "Description2", new Address("2 Rue des fleurs", "96512", "NewYork", "47.499088", "-0.582974"),
                    "12", true, Collections.singletonList("creationDb2"), "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 2");

            mRealEstateDao.insertRealEstate(new RealEstate("Duplex", 90000, "50", 16, 4,
                    2, "Description3", new Address("3 Rue des fleurs", "96512", "NewYork", "47.499088", "-0.582964"),
                    "3", true, Collections.singletonList("creationDb3"),
                    "12 / 2 / 2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 3");

            mRealEstateDao.insertRealEstate(new RealEstate("Castle", 260000, "135", 16, 4,
                    2, "Description4", new Address("4 Rue des fleurs", "96512", "NewYork", "47.499088", "-0.582954"),
                    "23", true, Collections.singletonList("creationDb4"),
                    "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 4");

            mRealEstateDao.insertRealEstate(new RealEstate("House", 450000, "320", 16, 4,
                    2, "Description5", new Address("5 Rue des fleurs", "96512", "NewYork", "47.499088", "-0.582944"),
                    "11", true, Collections.singletonList("creationDb5"),
                    "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 5");

            mRealEstateDao.insertRealEstate(new RealEstate("House", 360000, "480", 16, 4,
                    2, "Description6", new Address("6 Rue des fleurs", "96512", "NewYork", "47.499088", "-0.582934"),
                    "9", true, Collections.singletonList("creationDb6"),
                    "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 6");
            return null;
        }
    }
}
