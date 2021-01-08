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

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public float getSurface() {
        return mSurface;
    }

    public void setSurface(float mSurface) {
        this.mSurface = mSurface;
    }

    public int getPieceNumber() {
        return mPieceNumber;
    }

    public void setPieceNumber(int mPieceNumber) {
        this.mPieceNumber = mPieceNumber;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getPointOfInterest() {
        return mPointOfInterest;
    }

    public void setPointOfInterest(String mPointOfInterest) {
        this.mPointOfInterest = mPointOfInterest;
    }

    public boolean isBuy() {
        return mBuy;
    }

    public void setBuy(boolean mBuy) {
        this.mBuy = mBuy;
    }

    public String getIncommingDate() {
        return mIncommingDate;
    }

    public void setIncommingDate(String mIncommingDate) {
        this.mIncommingDate = mIncommingDate;
    }

    public String getDateOfSale() {
        return mDateOfSale;
    }

    public void setDateOfSale(String mDateOfSale) {
        this.mDateOfSale = mDateOfSale;
    }

    public String getRealEstateAgent() {
        return mRealEstateAgent;
    }

    public void setRealEstateAgent(String mRealEstateAgent) {
        this.mRealEstateAgent = mRealEstateAgent;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable mDrawable) {
        this.mDrawable = mDrawable;
    }
}
