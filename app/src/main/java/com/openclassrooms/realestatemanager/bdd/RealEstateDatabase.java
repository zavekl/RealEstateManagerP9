package com.openclassrooms.realestatemanager.bdd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;
import com.openclassrooms.realestatemanager.utils.Utils;

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
            Log.d(TAG, "getInstance: null so create it : ");
            instance = Room.databaseBuilder(context.getApplicationContext(), RealEstateDatabase.class, "real_estate_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        //TODO RESOUDRE CRASH SI LIGNEs ACTIVENT
//        InternalFilesRepository internalFilesRepository = ((MyApplication) context.getApplicationContext()).getContainerDependencies().getInternalFilesRepository();
//        InternalFilesRepository internalFilesRepository = ((MyApplication) context).getContainerDependencies().getInternalFilesRepository();

        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_one);
//        internalFilesRepository.setFile("one", Utils.BitMapToString(bitmap1));
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_two);
//        internalFilesRepository.setFile("two", Utils.BitMapToString(bitmap2));
        bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_three);
//        internalFilesRepository.setFile("three", Utils.BitMapToString(bitmap3));
        bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_four);
//        internalFilesRepository.setFile("four", Utils.BitMapToString(bitmap4));
        bitmap5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_five);
//        internalFilesRepository.setFile("five", Utils.BitMapToString(bitmap5));
        bitmap6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.house_six);
//        internalFilesRepository.setFile("six", Utils.BitMapToString(bitmap6));

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
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: ");
            mRealEstateDao.insertRealEstate(new RealEstate("Maison1", 120000, 520.25f, 16, "Description1",
                    "adresse1", "Rien", false, Utils.BitMapToString(bitmap1), "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 1");
            mRealEstateDao.insertRealEstate(new RealEstate("Maison2", 210000, 520.25f, 16, "Description2",
                    "adresse2", "Rien", false, Utils.BitMapToString(bitmap2), "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 2");
            mRealEstateDao.insertRealEstate(new RealEstate("Maison3", 170000, 520.25f, 16, "Description3",
                    "adresse3", "Rien", false, Utils.BitMapToString(bitmap3), "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 3");
            mRealEstateDao.insertRealEstate(new RealEstate("Maison4", 260000, 520.25f, 16, "Description4",
                    "adresse4", "Rien", false, Utils.BitMapToString(bitmap4), "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 4");
            mRealEstateDao.insertRealEstate(new RealEstate("Maison5", 190000, 520.25f, 16, "Description5",
                    "adresse5", "Rien", false, Utils.BitMapToString(bitmap5), "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 5");
            mRealEstateDao.insertRealEstate(new RealEstate("Maison6", 230000, 520.25f, 16, "Description6",
                    "adresse6", "Rien", false, Utils.BitMapToString(bitmap6), "12/2/2019", "0", "Jean"));
            Log.d(TAG, "PopulateDbAsyncTask : doInBackground: 6");
            return null;
        }
    }
}
