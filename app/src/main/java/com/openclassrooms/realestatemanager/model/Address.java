package com.openclassrooms.realestatemanager.model;

/**
 * Created by NIATEL Brice on 28/01/2021.
 */
public class Address {
    private String mNumber;
    private String mStreet;
    private String mPostalCode;
    private String mTown;

    public Address(String mNumber, String mStreet, String mPostalCode, String mTown) {
        this.mNumber = mNumber;
        this.mStreet = mStreet;
        this.mPostalCode = mPostalCode;
        this.mTown = mTown;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getStreet() {
        return mStreet;
    }

    public void setStreet(String mStreet) {
        this.mStreet = mStreet;
    }

    public String getPostalCode() {
        return mPostalCode;
    }

    public void setPostalCode(String mPostalCode) {
        this.mPostalCode = mPostalCode;
    }

    public String getTown() {
        return mTown;
    }

    public void setTown(String mTown) {
        this.mTown = mTown;
    }
}
