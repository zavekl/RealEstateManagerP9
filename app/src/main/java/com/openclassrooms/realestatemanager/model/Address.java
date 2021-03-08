package com.openclassrooms.realestatemanager.model;

/**
 * Created by NIATEL Brice on 28/01/2021.
 */
public class Address {
    private String mPostalCode;
    private String mTown;
    private String mNumberStreet;
    private String mLat;
    private String mLng;

    public Address(String numberStreet, String postalCode, String town, String lat, String lng) {
        this.mNumberStreet = numberStreet;
        this.mPostalCode = postalCode;
        this.mTown = town;
        this.mLat = lat;
        this.mLng = lng;
    }

    public Address(String numberStreet, String postalCode, String town) {
        this.mNumberStreet = numberStreet;
        this.mPostalCode = postalCode;
        this.mTown = town;
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

    public String getNumberStreet() {
        return mNumberStreet;
    }

    public void setNumberStreet(String mNumberStreet) {
        this.mNumberStreet = mNumberStreet;
    }

    public String getLat() {
        return mLat;
    }

    public void setLat(String mLat) {
        this.mLat = mLat;
    }

    public String getLng() {
        return mLng;
    }

    public void setLng(String mLng) {
        this.mLng = mLng;
    }

    @Override
    public String toString() {
        return "Address{" +
                "mPostalCode='" + mPostalCode + '\'' +
                ", mTown='" + mTown + '\'' +
                ", mNumberStreet='" + mNumberStreet + '\'' +
                ", mLat=" + mLat +
                ", mLng=" + mLng +
                '}';
    }
}

