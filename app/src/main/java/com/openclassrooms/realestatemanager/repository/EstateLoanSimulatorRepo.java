package com.openclassrooms.realestatemanager.repository;

import android.util.Log;

/**
 * Created by NIATEL Brice on 12/04/2021.
 */
public class EstateLoanSimulatorRepo {

    private static final String TAG = "EstateLoanSimulatorRepo";

    private String mRealEstatePrice;
    private String mInterestRate;
    private String mTimeCreditYear;

    public int calculateEstateLoanPerMonth() {
        // M = [mRealEstatePrice × (mInterestRate/12)]/[1 – (1 + (mInterestRate/12)) -(12 × mTimeCreditYear)]

        double interestRateFormatted = Double.parseDouble(mInterestRate) / 100.0; //exemple : 1% --> 0.01

        int result = (int) ((Double.parseDouble(mRealEstatePrice) * (interestRateFormatted / 12.0)) /
                (1.0 - (Math.pow(1.0 + (interestRateFormatted / 12.0), -(12.0 * Double.parseDouble(mTimeCreditYear))))));
        Log.d(TAG, "calculateEstateLoan: " + result);
        return result;
    }

    public int calculateCostOfCredit() {
        return (int) (calculateEstateLoanPerMonth() * (Double.parseDouble(mTimeCreditYear) * 12)
                - Double.parseDouble(mRealEstatePrice));
    }

    public void setRealEstatePrice(String realEstatePrice) {
        mRealEstatePrice = realEstatePrice;
    }

    public void setInterestRate(String interestRate) {
        mInterestRate = interestRate;
    }

    public void setTimeCreditYear(String timeCreditYear) {
        mTimeCreditYear = timeCreditYear;
    }
}
