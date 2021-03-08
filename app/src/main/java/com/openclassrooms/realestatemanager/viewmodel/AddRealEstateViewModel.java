package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;
import com.openclassrooms.realestatemanager.repository.RealEstateRepository;
import com.openclassrooms.realestatemanager.utils.TextInputUtils;

import java.util.List;

public class AddRealEstateViewModel extends AndroidViewModel {
    private TextInputUtils mTextInputUtils;

    private final RealEstateRepository mRealEstateRepository;
    private final InternalFilesRepository mIternalFilesRepository;

    public AddRealEstateViewModel(@NonNull Application application) {
        super(application);
        mRealEstateRepository = ((MyApplication) application).getContainerDependencies().getRealEstateRepository();
        mIternalFilesRepository = new InternalFilesRepository(application);
    }

    public void createTextInputUtils(AutoCompleteTextView autoCompleteTextView, TextInputEditText price, TextInputEditText description,
                                     TextInputEditText numberAndStreet, TextInputEditText postalNumber, TextInputEditText town,
                                     TextInputEditText surface, TextInputEditText room, TextInputEditText bedroom, TextInputEditText
                                             bathroom, Context context) {
        mTextInputUtils = new TextInputUtils(autoCompleteTextView, price, description, numberAndStreet, postalNumber,
                town, surface, room, bedroom, bathroom, context);
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
}