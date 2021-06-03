package com.openclassrooms.realestatemanager.bdd;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.openclassrooms.realestatemanager.model.Converters;
import com.openclassrooms.realestatemanager.model.RealEstate;

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
                    .build();
        }
        Log.d(TAG, "getInstance: end of getInstance");
        return instance;
    }
}
