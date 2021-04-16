package com.openclassrooms.realestatemanager.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by NIATEL Brice on 17/03/2021.
 */
public class Criteria implements Serializable {

    private String mType;
    private String mMinPrice;
    private String mMaxPrice;
    private String mMinSurface;
    private String mMaxSurface;
    private boolean mAvailable;
    private String mRoomNumber;
    private String mPoi;

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public String getMinPrice() {
        return mMinPrice;
    }

    public void setMinPrice(String mMinPrice) {
        this.mMinPrice = mMinPrice;
    }

    public String getMaxPrice() {
        return mMaxPrice;
    }

    public void setMaxPrice(String mMaxPrice) {
        this.mMaxPrice = mMaxPrice;
    }

    public String getMinSurface() {
        return mMinSurface;
    }

    public void setMinSurface(String mMinSurface) {
        this.mMinSurface = mMinSurface;
    }

    public String getMaxSurface() {
        return mMaxSurface;
    }

    public void setMaxSurface(String mMaxSurface) {
        this.mMaxSurface = mMaxSurface;
    }

    public boolean getAvailable() {
        return mAvailable;
    }

    public void setAvailable(boolean mAvailable) {
        this.mAvailable = mAvailable;
    }

    public String getRoomNumber() {
        return mRoomNumber;
    }

    public void setRoomNumber(String mRoomNumber) {
        this.mRoomNumber = mRoomNumber;
    }

    public String getPoi() {
        return mPoi;
    }

    public void setPoi(String mPoi) {
        this.mPoi = mPoi;
    }

    @NonNull
    @Override
    public String toString() {
        return "Criteria{" +
                "mType='" + mType + '\'' +
                ", mMinPrice='" + mMinPrice + '\'' +
                ", mMaxPrice='" + mMaxPrice + '\'' +
                ", mMinSurface='" + mMinSurface + '\'' +
                ", mMaxSurface='" + mMaxSurface + '\'' +
                ", mAvailable=" + mAvailable +
                ", mRoomNumber='" + mRoomNumber + '\'' +
                ", mPoi='" + mPoi + '\'' +
                '}';
    }
}
