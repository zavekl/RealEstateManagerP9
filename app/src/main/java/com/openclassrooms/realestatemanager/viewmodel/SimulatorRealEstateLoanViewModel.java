package com.openclassrooms.realestatemanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.repository.EstateLoanSimulatorRepo;

public class SimulatorRealEstateLoanViewModel extends AndroidViewModel {

    private final EstateLoanSimulatorRepo mEstateLoanSimulatorRepo;

    public SimulatorRealEstateLoanViewModel(@NonNull Application application) {
        super(application);
        mEstateLoanSimulatorRepo = ((MyApplication) application).getContainerDependencies().getEstateLoanSimulatorRepo();
    }

    public int calculateRealEstateLoan() {
        return mEstateLoanSimulatorRepo.calculateEstateLoanPerMonth();
    }

    public int calculateCostOfCredit() {
        return mEstateLoanSimulatorRepo.calculateCostOfCredit();
    }

    public void setRealEstatePrice(String realEstatePrice) {
        mEstateLoanSimulatorRepo.setRealEstatePrice(realEstatePrice);
    }

    public void setInterestRate(String interestRate) {
        mEstateLoanSimulatorRepo.setInterestRate(interestRate);
    }

    public void setTimeCreditYear(String timeCreditYear) {
        mEstateLoanSimulatorRepo.setTimeCreditYear(timeCreditYear);
    }
}