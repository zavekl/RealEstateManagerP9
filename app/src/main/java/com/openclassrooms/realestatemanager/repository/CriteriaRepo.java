package com.openclassrooms.realestatemanager.repository;

import android.util.Log;

import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NIATEL Brice on 24/03/2021.
 */
public class CriteriaRepo {

    private static final String TAG = "CriteriaRepo";

    private Criteria mCriteria = new Criteria();

    private final List<RealEstate> mRealEstatesList = new ArrayList<>();
    private final List<RealEstate> mRealEstatesListBackUp = new ArrayList<>();

    //Check if they are criteria
    public boolean isCriteria() {
        if (mCriteria != null) {
            if (!mCriteria.getType().equals("") || !mCriteria.getMinPrice().equals("") || !mCriteria.getMaxPrice().equals("") ||
                    !mCriteria.getMinSurface().equals("") || !mCriteria.getMaxSurface().equals("") || !mCriteria.getAvailable() ||
                    !mCriteria.getRoomNumber().equals("") || !mCriteria.getPoi().equals("")) {
                Log.d(TAG, "isCriteria: true");
                return true;
            } else {
                Log.d(TAG, "isCriteria: false");
                return false;
            }
        } else {
            Log.d(TAG, "isCriteria: no criteria set");
            return false;
        }
    }

    public List<RealEstate> filterAllParameters() {
        mRealEstatesList.clear();
        mRealEstatesList.addAll(mRealEstatesListBackUp);

        if (!mCriteria.getType().equals("")) {
            Log.d(TAG, "filterAllParameters: getType");
            filterByType(mCriteria.getType());
        }

        if (!mCriteria.getMinPrice().equals("") && !mCriteria.getMaxPrice().equals("")) {
            Log.d(TAG, "filterAllParameters: getMinPrice");
            filterByPrice(mCriteria.getMinPrice(), mCriteria.getMaxPrice());
        }

        if (!mCriteria.getMinSurface().equals("") && !mCriteria.getMaxSurface().equals("")) {
            Log.d(TAG, "filterAllParameters: getMinSurface + getMaxSurface");
            filterBySurface(mCriteria.getMinSurface(), mCriteria.getMaxSurface());
        }

        Log.d(TAG, "filterAllParameters: getAvailable" + mCriteria.getAvailable());
        filterByAvailablility(mCriteria.getAvailable());

        if (!mCriteria.getRoomNumber().equals("")) {
            Log.d(TAG, "filterAllParameters: getRoomNumber");
            filterByRoomNumber(Integer.parseInt(mCriteria.getRoomNumber()));
        }

        if (!mCriteria.getPoi().equals("")) {
            Log.d(TAG, "filterAllParameters: getPoi");
            filterByPoiNumber(Integer.parseInt(mCriteria.getPoi()));
        }

        Log.d(TAG, "filterAllParameters: list filtered : " + mRealEstatesList.size());

        if (mRealEstatesList.size() > 0) {
            Log.d(TAG, "filterAllParameters: apply criteria");
            return mRealEstatesList;
        } else {
            Log.d(TAG, "filterAllParameters: return list backup");
            return mRealEstatesListBackUp;
        }
    }

    //Filter by type
    public void filterByType(String param) {
        List<RealEstate> filteredList = new ArrayList<>();
        for (RealEstate realEstate : mRealEstatesList) {
            if (realEstate.getType().contains(param)) {
                filteredList.add(realEstate);
            }
        }

        clearList(filteredList);
    }

    //Filter by price between min and max
    public void filterByPrice(String param1, String param2) {
        Log.d(TAG, "filterByPrice: params : " + param1 + "/" + param2);
        List<RealEstate> filteredList = new ArrayList<>();
        for (RealEstate realEstate : mRealEstatesList) {
            if (realEstate.getPrice() >= Integer.parseInt(param1) && realEstate.getPrice() <= Integer.parseInt(param2)) {
                Log.d(TAG, "filterByPrice: " + realEstate.getPrice());
                filteredList.add(realEstate);
            }
        }

        clearList(filteredList);
    }

    //Filter by surface between min and max
    public void filterBySurface(String param1, String param2) {
        Log.d(TAG, "filterBySurface: params : " + param1 + "/" + param2);
        List<RealEstate> filteredList = new ArrayList<>();
        for (RealEstate realEstate : mRealEstatesList) {
            if (Integer.parseInt(realEstate.getSurface()) >= Integer.parseInt(param1)
                    && Integer.parseInt(realEstate.getSurface()) <= Integer.parseInt(param2)) {
                filteredList.add(realEstate);
            }
        }

        clearList(filteredList);
    }

    //Filter by availability
    public void filterByAvailablility(boolean param) {
        List<RealEstate> filteredList = new ArrayList<>();
        for (RealEstate realEstate : mRealEstatesList) {
            if (realEstate.isBuy() == param) {
                filteredList.add(realEstate);
            }
        }

        //TODO ERREUR ICI NE PREND PAS EN COMPTE LE BOOLEAN
        Log.d(TAG, "filterByAvailablility: " + filteredList.size());
        clearList(filteredList);
    }

    //Filter by number of room at least
    public void filterByRoomNumber(int param) {
        List<RealEstate> filteredList = new ArrayList<>();
        for (RealEstate realEstate : mRealEstatesList) {
            if (realEstate.getPieceNumber() <= param) {
                filteredList.add(realEstate);
            }
        }

        clearList(filteredList);
    }

    //Filter by number of room at least
    public void filterByPoiNumber(int param) {
        List<RealEstate> filteredList = new ArrayList<>();
        for (RealEstate realEstate : mRealEstatesList) {
            if (Integer.parseInt(realEstate.getPointOfInterest()) <= param) {
                filteredList.add(realEstate);
            }
        }

        clearList(filteredList);
    }

    private void clearList(List<RealEstate> filteredList) {
        if (filteredList.size() > 0) {
            mRealEstatesList.clear();
            mRealEstatesList.addAll(filteredList);
            filteredList.clear();
        }
    }

    //Getters and Setters
    public Criteria getCriteria() {
        return mCriteria;
    }

    public void setCriteria(Criteria mCriteria) {
        this.mCriteria = mCriteria;
    }

    public List<RealEstate> getRealEstatesListBackUp() {
        Log.d(TAG, "getRealEstatesListBackUp: " + mRealEstatesListBackUp.size());
        return mRealEstatesListBackUp;
    }

    public void setRealEstatesList(List<RealEstate> realEstatesList) {
        mRealEstatesList.clear();
        mRealEstatesListBackUp.clear();
        mRealEstatesList.addAll(realEstatesList);
        mRealEstatesListBackUp.addAll(realEstatesList);
    }
}