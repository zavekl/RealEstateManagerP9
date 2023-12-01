package com.openclassrooms.realestatemanager.model;

import android.content.ContentValues;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.openclassrooms.realestatemanager.utils.Utils;

import java.util.List;

/**
 * Created by <NIATEL Brice> on <16/12/2020>.
 */
@Entity(tableName = "realestate")
public class RealEstate {
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
    @ColumnInfo(name = "availability")
    private boolean mAvailability;
    @ColumnInfo(name = "image")
    private List<String> mListPathImage;
    @ColumnInfo(name = "incoming_date")
    private String mIncomingDate;
    @ColumnInfo(name = "date_sale")
    private String mDateOfSale;
    @ColumnInfo(name = "agent")
    private String mRealEstateAgent;

    public RealEstate(String mType, int mPrice, String mSurface, int mPieceNumber, int mBedroomNumber, int mBathroomNumber,
                      String mDescription, Address mAddress, String mPointOfInterest, boolean mAvailability, List<String> mListPathImage, String mIncomingDate,
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
        this.mAvailability = mAvailability;
        this.mListPathImage = mListPathImage;
        this.mIncomingDate = mIncomingDate;
        this.mDateOfSale = mDateOfSale;
        this.mRealEstateAgent = mRealEstateAgent;
    }
    @Ignore
    public RealEstate() {
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

    public boolean isAvailability() {
        return mAvailability;
    }

    public void setAvailability(boolean buy) {
        this.mAvailability = buy;
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

    public List<String> getListPathImage() {
        return mListPathImage;
    }

    public void setListPathImage(List<String> image) {
        this.mListPathImage = image;
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

    //Convert Values to RealEstate object
    public static RealEstate fromContentValues(ContentValues values) {
        final RealEstate realEstate = new RealEstate();
        if (values.containsKey("type")) realEstate.setType(values.getAsString("type"));
        if (values.containsKey("price")) realEstate.setPrice(values.getAsInteger("price"));
        if (values.containsKey("surface")) realEstate.setSurface(values.getAsString("surface"));
        if (values.containsKey("piece_number"))
            realEstate.setPieceNumber(values.getAsInteger("piece_number"));
        if (values.containsKey("bedroom_number"))
            realEstate.setBedroomNumber(values.getAsInteger("bedroom_number"));
        if (values.containsKey("bathroom_number"))
            realEstate.setBathroomNumber(values.getAsInteger("bathroom_number"));
        if (values.containsKey("description"))
            realEstate.setDescription(values.getAsString("description"));
        if (values.containsKey("address"))
            realEstate.setAddress(Converters.stringToAddress(values.getAsString("address")));
        if (values.containsKey("pointofinterest"))
            realEstate.setPointOfInterest(values.getAsString("pointofinterest"));
        if (values.containsKey("availability"))
            realEstate.setAvailability(values.getAsBoolean("availability"));
        if (values.containsKey("image"))
            realEstate.setListPathImage(Utils.stringToPhotoList(values.getAsString("image")));
        if (values.containsKey("incoming_date"))
            realEstate.setIncomingDate(values.getAsString("incoming_date"));
        if (values.containsKey("date_sale"))
            realEstate.setDateOfSale(values.getAsString("date_sale"));
        if (values.containsKey("agent")) realEstate.setRealEstateAgent(values.getAsString("agent"));
        return realEstate;
    }
}
