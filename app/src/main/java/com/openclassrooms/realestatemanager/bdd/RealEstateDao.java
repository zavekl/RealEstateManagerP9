package com.openclassrooms.realestatemanager.bdd;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.List;

/**
 * Created by <NIATEL Brice> on <19/01/2021>.
 */
@Dao
public interface RealEstateDao {
    @Insert
    void insertRealEstate(RealEstate realEstate);

    @Update
    void updateRealEstate(RealEstate realEstate);

    @Delete
    void deleteRealEstate(RealEstate realEstate);

    @Query("SELECT * FROM realEstate")
    LiveData<List<RealEstate>> getAllRealEstate();

    @Query("SELECT * FROM realEstate WHERE id=:id")
    LiveData<RealEstate> getRealEstateById(long id);

    @Query("SELECT * FROM realEstate WHERE id=:id")
    Cursor getRealEstateByIdCursor(long id);
}
