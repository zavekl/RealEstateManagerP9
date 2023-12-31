package com.openclassrooms.realestatemanager.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.openclassrooms.realestatemanager.model.Criteria;

/**
 * Created by NIATEL Brice on 16/04/2021.
 */
public class ApplicationPreferencesRepo {
    private static final String TAG = "ApplicationPreferences";
    private static final String PREF_CRITERIA = "CRITERIA_ID";
    private static final String PREF_DESCRIPTION = "DESCRIPTION_ID";
    private static final String PREF_PHOTO_INTENT = "PHOTO_INTENT_ID";

    private final Context mContext;

    public ApplicationPreferencesRepo(Context Context) {
        this.mContext = Context;
    }

    //Delete sharedpref criteria
    public void deleteSharedPrefsCriteria() {
        Log.d(TAG, "deleteSharedPrefsCriteria: ");
        final SharedPreferences sharedPref = mContext.getSharedPreferences(PREF_CRITERIA, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        editor.clear().apply();
    }

    //Set sharedpref criteria
    public void setSharedPrefsCriteria(final Criteria criteria) {
        Log.d(TAG, "setSharedPrefsCriteria: ");
        final SharedPreferences sharedPref = mContext.getSharedPreferences(PREF_CRITERIA, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        if (criteria.getType() != null && !criteria.getType().equals("")) {
            Log.d(TAG, "setSharedPrefsCriteria: type");
            editor.putString("criteria_type", criteria.getType());
        }
        if (criteria.getMinPrice() != null && !criteria.getMinPrice().equals("")) {
            Log.d(TAG, "setSharedPrefsCriteria: min price");
            editor.putString("criteria_priceMin", criteria.getMinPrice());
        }
        if (criteria.getMaxPrice() != null && !criteria.getMaxPrice().equals("")) {
            Log.d(TAG, "setSharedPrefsCriteria: max price");
            editor.putString("criteria_priceMax", criteria.getMaxPrice());
        }
        if (criteria.getMinSurface() != null && !criteria.getMinSurface().equals("")) {
            Log.d(TAG, "setSharedPrefsCriteria: min surface");
            editor.putString("criteria_surfaceMin", criteria.getMinSurface());
        }
        if (criteria.getMaxSurface() != null && !criteria.getMaxSurface().equals("")) {
            Log.d(TAG, "setSharedPrefsCriteria: max surface");
            editor.putString("criteria_surfaceMax", criteria.getMaxSurface());
        }
        Log.d(TAG, "setSharedPrefsCriteria: criteria_availability");
        editor.putBoolean("criteria_availability", criteria.getAvailable());

        if (criteria.getRoomNumber() != null && !criteria.getRoomNumber().equals("")) {
            Log.d(TAG, "setSharedPrefsCriteria: room number");
            editor.putString("criteria_roomNumber", criteria.getRoomNumber());
        }
        if (criteria.getPoi() != null && !criteria.getPoi().equals("")) {
            Log.d(TAG, "setSharedPrefsCriteria: poi number");
            editor.putString("criteria_poi", criteria.getPoi());
        }

        editor.apply();
    }
    //Get sharedpref criteria
    public Criteria getSharedPrefsCriteria() {
        Log.d(TAG, "getSharedPrefsCriteria: ");
        final SharedPreferences sharedPref = mContext.getSharedPreferences(PREF_CRITERIA, Context.MODE_PRIVATE);
        final Criteria criteria = new Criteria();
        criteria.setType(sharedPref.getString("criteria_type", null));
        criteria.setMinPrice(sharedPref.getString("criteria_priceMin", null));
        criteria.setMaxPrice(sharedPref.getString("criteria_priceMax", null));
        criteria.setMinSurface(sharedPref.getString("criteria_surfaceMin", null));
        criteria.setMaxSurface(sharedPref.getString("criteria_surfaceMax", null));
        criteria.setAvailable(sharedPref.getBoolean("criteria_availability", true));
        criteria.setRoomNumber(sharedPref.getString("criteria_roomNumber", null));
        criteria.setPoi(sharedPref.getString("criteria_poi", null));
        Log.d(TAG, "getSharedPrefsCriteria: " + criteria.toString());
        return criteria;
    }

    //Get sharedpref description
    public String getSharedPrefsFirstItemDescription() {
        final SharedPreferences sharedPref = mContext.getSharedPreferences(PREF_DESCRIPTION, Context.MODE_PRIVATE);
        return sharedPref.getString("first_item_description", null);
    }

    //Set sharedpref description
    public void setSharedPrefsFirstItemDescription(final String s) {
        final SharedPreferences sharedPref = mContext.getSharedPreferences(PREF_DESCRIPTION, Context.MODE_PRIVATE);
        sharedPref.edit().putString("first_item_description", s).apply();
    }

    //Get sharedpref photo
    public boolean getSharedPrefsPhotoIntent() {
        final SharedPreferences sharedPref = mContext.getSharedPreferences(PREF_PHOTO_INTENT, Context.MODE_PRIVATE);
        return sharedPref.getBoolean("photo_intent", false);
    }

    //Set sharedpref photo
    public void setSharedPrefsPhotoIntent() {
        final SharedPreferences sharedPref = mContext.getSharedPreferences(PREF_PHOTO_INTENT, Context.MODE_PRIVATE);
        sharedPref.edit().putBoolean("photo_intent", true).apply();
    }

    //Delete sharedpref photo
    public void deleteSharedPrefsPhotoIntent() {
        Log.d(TAG, "deleteSharedPrefsCriteria: ");
        SharedPreferences sharedPref = mContext.getSharedPreferences(PREF_PHOTO_INTENT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.clear().apply();
    }
}
