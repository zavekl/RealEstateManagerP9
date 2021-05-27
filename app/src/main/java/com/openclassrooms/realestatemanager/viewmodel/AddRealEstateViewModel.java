package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.projo.NearByPlaceResults;
import com.openclassrooms.realestatemanager.repository.ApplicationPreferencesRepo;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;
import com.openclassrooms.realestatemanager.repository.NotificationRepo;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;
import com.openclassrooms.realestatemanager.repository.RetrofitRepository;
import com.openclassrooms.realestatemanager.utils.TextInputUtils;

import java.util.List;

public class AddRealEstateViewModel extends AndroidViewModel {
    private TextInputUtils mTextInputUtils;

    private final RealEstateRepository mRealEstateRepository;
    private final InternalFilesRepository mIternalFilesRepository;
    private final RetrofitRepository mRetrofitRepository;
    private final NotificationRepo mNotificationRepo;
    private final ApplicationPreferencesRepo mApplicationPreferencesRepo;

    public AddRealEstateViewModel(@NonNull Application application) {
        super(application);
        mRealEstateRepository = ((MyApplication) application).getContainerDependencies().getRealEstateRepository();
        mRetrofitRepository = ((MyApplication) application).getContainerDependencies().getRetrofitRepository();
        mIternalFilesRepository = new InternalFilesRepository(application);
        mNotificationRepo = ((MyApplication) application).getContainerDependencies().getNotificationRepo();
        mApplicationPreferencesRepo = ((MyApplication) application).getContainerDependencies().getApplicationPreferencesRepo();
    }

    public void createTextInputUtils(AutoCompleteTextView autoCompleteTextView, TextInputEditText price, TextInputEditText description,
                                     TextInputEditText surface, TextInputEditText room, TextInputEditText bedroom, TextInputEditText
                                             bathroom, TextInputEditText address, Context context) {
        mTextInputUtils = new TextInputUtils(autoCompleteTextView, price, description, surface, room, bedroom, bathroom, address, context);
    }

    public boolean validateTextInput() {
        return mTextInputUtils.validateAllParameters();
    }

    public List<String> getItems() {
        return mTextInputUtils.getItems();
    }

    public void insert(RealEstate realEstate) {
        mRealEstateRepository.insert(realEstate);
    }

    public void setImageInInternalMemory(String name, Bitmap bitmap) {
        mIternalFilesRepository.setFile(name, bitmap);
    }

    public LiveData<NearByPlaceResults> getPOIAroundUser(String lat, String lng) {
        return mRetrofitRepository.getPOIAroundUser(lat + "," + lng);
    }

    public void sendNotification() {
        mNotificationRepo.sendNotification();
    }

    public void setSharedPrefIntentPhoto() {
        mApplicationPreferencesRepo.setSharedPrefsPhotoIntent();
    }
}