package com.openclassrooms.realestatemanager.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by <NIATEL Brice> on <16/12/2020>.
 */
@Entity(tableName = "realestate")
public class RealEstate {
    //TODO avec la classe address pour généraliser?
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "type")
    private String mType;
    @ColumnInfo(name = "price")
    private int mPrice;
    @ColumnInfo(name = "surface")
    private float mSurface;
    @ColumnInfo(name = "piece_number")
    private int mPieceNumber;
    @ColumnInfo(name = "description")
    private String mDescription;
    @ColumnInfo(name = "address")
    private String mAddress;
    @ColumnInfo(name = "pointofinterest")
    private String mPointOfInterest;
    @ColumnInfo(name = "buy")
    private boolean mBuy;
    @ColumnInfo(name = "image")
    private String mImage;
    @ColumnInfo(name = "incoming_date")
    private String mIncomingDate;
    @ColumnInfo(name = "date_sale")
    private String mDateOfSale;
    @ColumnInfo(name = "agent")
    private String mRealEstateAgent;

    public RealEstate(String mType, int mPrice, float mSurface, int mPieceNumber, String mDescription, String mAddress, String mPointOfInterest,
                      boolean mBuy, String image, String mIncomingDate, String mDateOfSale, String mRealEstateAgent) {
        this.mType = mType;
        this.mPrice = mPrice;
        this.mSurface = mSurface;
        this.mPieceNumber = mPieceNumber;
        this.mDescription = mDescription;
        this.mAddress = mAddress;
        this.mPointOfInterest = mPointOfInterest;
        this.mImage = image;
        this.mBuy = mBuy;
        this.mIncomingDate = mIncomingDate;
        this.mDateOfSale = mDateOfSale;
        this.mRealEstateAgent = mRealEstateAgent;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        this.mPrice = price;
    }

    public float getSurface() {
        return mSurface;
    }

    public void setSurface(float surface) {
        this.mSurface = surface;
    }

    public int getPieceNumber() {
        return mPieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.mPieceNumber = pieceNumber;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getPointOfInterest() {
        return mPointOfInterest;
    }

    public void setPointOfInterest(String pointOfInterest) {
        this.mPointOfInterest = pointOfInterest;
    }

    public boolean isBuy() {
        return mBuy;
    }

    public void setBuy(boolean buy) {
        this.mBuy = buy;
    }

    public String getIncomingDate() {
        return mIncomingDate;
    }

    public void setIncomingDate(String incomingDate) {
        this.mIncomingDate = incomingDate;
    }

    public String getDateOfSale() {
        return mDateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.mDateOfSale = dateOfSale;
    }

    public String getRealEstateAgent() {
        return mRealEstateAgent;
    }

    public void setRealEstateAgent(String realEstateAgent) {
        this.mRealEstateAgent = realEstateAgent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }
}
