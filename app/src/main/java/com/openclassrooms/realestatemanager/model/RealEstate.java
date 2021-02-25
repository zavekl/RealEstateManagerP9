package com.openclassrooms.realestatemanager.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

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
    private String mSurface;
    @ColumnInfo(name = "piece_number")
    private int mPieceNumber;
    @ColumnInfo(name = "bedroom_number")
    private int mBedroomNumber;
    @ColumnInfo(name = "bathroom_number")
    private int mBathroomNumber;
    @ColumnInfo(name = "description")
    private String mDescription;
    @ColumnInfo(name = "address")
    private Address mAddress;
    @ColumnInfo(name = "pointofinterest")
    private String mPointOfInterest;
    @ColumnInfo(name = "buy")
    private boolean mBuy;
    @ColumnInfo(name = "image")
    private List<String> mImage;
    @ColumnInfo(name = "incoming_date")
    private String mIncomingDate;
    @ColumnInfo(name = "date_sale")
    private String mDateOfSale;
    @ColumnInfo(name = "agent")
    private String mRealEstateAgent;

    public RealEstate(String mType, int mPrice, String mSurface, int mPieceNumber, int mBedroomNumber, int mBathroomNumber,
                      String mDescription, Address mAddress, String mPointOfInterest, boolean mBuy, List<String> mImage, String mIncomingDate,
                      String mDateOfSale, String mRealEstateAgent) {
        this.mType = mType;
        this.mPrice = mPrice;
        this.mSurface = mSurface;
        this.mPieceNumber = mPieceNumber;
        this.mBedroomNumber = mBedroomNumber;
        this.mBathroomNumber = mBathroomNumber;
        this.mDescription = mDescription;
        this.mAddress = mAddress;
        this.mPointOfInterest = mPointOfInterest;
        this.mBuy = mBuy;
        this.mImage = mImage;
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

    public String getSurface() {
        return mSurface;
    }

    public void setSurface(String surface) {
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

    public Address getAddress() {
        return mAddress;
    }

    public void setAddress(Address address) {
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

    public List<String> getImage() {
        return mImage;
    }

    public void setImage(List<String> image) {
        this.mImage = image;
    }

    public int getBedroomNumber() {
        return mBedroomNumber;
    }

    public void setBedroomNumber(int mBedroomNumber) {
        this.mBedroomNumber = mBedroomNumber;
    }

    public int getBathroomNumber() {
        return mBathroomNumber;
    }

    public void setBathroomNumber(int mBathroomNumber) {
        this.mBathroomNumber = mBathroomNumber;
    }
}
