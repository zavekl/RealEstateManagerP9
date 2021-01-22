package com.openclassrooms.realestatemanager.bdd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.Utils;

/**
 * Created by <NIATEL Brice> on <19/01/2021>.
 */
@Database(entities = {RealEstate.class}, version = 1, exportSchema = false)
public abstract class RealEstateDatabase extends RoomDatabase {
    private static final String TAG = "RealEstateDatabase";
    private static Bitmap bitmap1, bitmap2, bitmap3, bitmap4, bitmap5, bitmap6;
    private static RealEstateDatabase instance;

    public abstract RealEstateDao realEstateDao();

    public static synchronized RealEstateDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), RealEstateDatabase.class, "real_estate_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_one);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_two);
        bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_three);
        bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_four);
        bitmap5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_five);
        bitmap6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_six);
        return instance;
    }

    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d(TAG, "onCreate: ");
            new CheckDbAsyncTask(instance).execute();
//            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class CheckDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final RealEstateDao mRealEstateDao;
        private final RealEstateDatabase mRealEstateDatabase;

        public CheckDbAsyncTask(RealEstateDatabase db) {
            mRealEstateDatabase = db;
            mRealEstateDao = db.realEstateDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: " + mRealEstateDao.getRealEstates().getValue());
            if (mRealEstateDao.getRealEstates().getValue() == null) {
                new PopulateDbAsyncTask(mRealEstateDatabase).execute();
            }
            return null;
        }
    }

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final RealEstateDao mRealEstateDao;

        private PopulateDbAsyncTask(RealEstateDatabase db) {
            mRealEstateDao = db.realEstateDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: ");
            mRealEstateDao.insertRealEstate(new RealEstate("Maison1", 120000, 520.25f, 16, "Description1",
                    "adresse1", "Rien", false, Utils.BitMapToString(bitmap1), "12/2/2019", "0", "Jean"));
            mRealEstateDao.insertRealEstate(new RealEstate("Maison2", 210000, 520.25f, 16, "Description2",
                    "adresse2", "Rien", false, Utils.BitMapToString(bitmap2), "12/2/2019", "0", "Jean"));
            mRealEstateDao.insertRealEstate(new RealEstate("Maison3", 170000, 520.25f, 16, "Description3",
                    "adresse3", "Rien", false, Utils.BitMapToString(bitmap3), "12/2/2019", "0", "Jean"));
            mRealEstateDao.insertRealEstate(new RealEstate("Maison4", 260000, 520.25f, 16, "Description4",
                    "adresse4", "Rien", false, Utils.BitMapToString(bitmap4), "12/2/2019", "0", "Jean"));
            mRealEstateDao.insertRealEstate(new RealEstate("Maison5", 190000, 520.25f, 16, "Description5",
                    "adresse5", "Rien", false, Utils.BitMapToString(bitmap5), "12/2/2019", "0", "Jean"));
            mRealEstateDao.insertRealEstate(new RealEstate("Maison6", 230000, 520.25f, 16, "Description6",
                    "adresse6", "Rien", false, Utils.BitMapToString(bitmap6), "12/2/2019", "0", "Jean"));
            return null;
        }
    }
}
