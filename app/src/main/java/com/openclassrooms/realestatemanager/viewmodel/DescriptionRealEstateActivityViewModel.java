package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;

/**
 * Created by <NIATEL Brice> on <21/01/2021>.
 */
public class DescriptionRealEstateActivityViewModel extends AndroidViewModel {
    private final RealEstateRepository mRealEstateRepository;
    private final InternalFilesRepository mIternalFilesRepository;

    public DescriptionRealEstateActivityViewModel(@NonNull Application application) {
        super(application);
        mRealEstateRepository = new RealEstateRepository(application);
        mIternalFilesRepository = new InternalFilesRepository(application);
    }

    public LiveData<RealEstate> getRealestateById(long id) {
        return mRealEstateRepository.getRealEstateById(id);
    }

    public Bitmap getBitmap(String file) {
        return mIternalFilesRepository.getFile(file);
    }

}
