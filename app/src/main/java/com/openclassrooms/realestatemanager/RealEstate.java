package com.openclassrooms.realestatemanager;

import android.graphics.drawable.Drawable;

/**
 * Created by <NIATEL Brice> on <16/12/2020>.
 */
public class RealEstate{
    //TODO avec la classe address pour généraliser?
    private String mType;
    private int mPrice;
    private float mSurface;
    private int mPieceNumber;
    private String mDescription;
    private String mAddress;
    private String mPointOfInterest;
    private boolean mBuy;
    private Drawable mDrawable;
    private String mIncommingDate;
    private String mDateOfSale;
    private String mRealEstateAgent;

    public RealEstate(String mType, int mPrice, float mSurface, int mPieceNumber, String mDescription, String mAddress, String mPointOfInterest,
                      Drawable mDrawable, boolean mBuy, String mIncommingDate, String mDateOfSale, String mRealEstateAgent) {
        this.mType = mType;
        this.mPrice = mPrice;
        this.mSurface = mSurface;
        this.mPieceNumber = mPieceNumber;
        this.mDescription = mDescription;
        this.mAddress = mAddress;
        this.mPointOfInterest = mPointOfInterest;
        this.mDrawable = mDrawable;
        this.mBuy = mBuy;
        this.mIncommingDate = mIncommingDate;
        this.mDateOfSale = mDateOfSale;
        this.mRealEstateAgent = mRealEstateAgent;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public float getmSurface() {
        return mSurface;
    }

    public void setmSurface(float mSurface) {
        this.mSurface = mSurface;
    }

    public int getmPieceNumber() {
        return mPieceNumber;
    }

    public void setmPieceNumber(int mPieceNumber) {
        this.mPieceNumber = mPieceNumber;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmPointOfInterest() {
        return mPointOfInterest;
    }

    public void setmPointOfInterest(String mPointOfInterest) {
        this.mPointOfInterest = mPointOfInterest;
    }

    public boolean ismBuy() {
        return mBuy;
    }

    public void setmBuy(boolean mBuy) {
        this.mBuy = mBuy;
    }

    public String getmIncommingDate() {
        return mIncommingDate;
    }

    public void setmIncommingDate(String mIncommingDate) {
        this.mIncommingDate = mIncommingDate;
    }

    public String getmDateOfSale() {
        return mDateOfSale;
    }

    public void setmDateOfSale(String mDateOfSale) {
        this.mDateOfSale = mDateOfSale;
    }

    public String getmRealEstateAgent() {
        return mRealEstateAgent;
    }

    public void setmRealEstateAgent(String mRealEstateAgent) {
        this.mRealEstateAgent = mRealEstateAgent;
    }

    public Drawable getmDrawable() {
        return mDrawable;
    }

    public void setmDrawable(Drawable mDrawable) {
        this.mDrawable = mDrawable;
    }

}
