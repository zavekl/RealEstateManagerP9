package com.openclassrooms.realestatemanager.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.openclassrooms.realestatemanager.model.Criteria;

/**
 * Created by NIATEL Brice on 25/03/2021.
 */
public class CriteriaReceiver extends BroadcastReceiver {

    private static final String TAG = "CriteriaReceiver";

    ICustomListener mCallback = null;

    public static final String APPLY_CRITERIA = "APPLY_CRITERIA";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(APPLY_CRITERIA)) {
            Log.d(TAG, "onReceive: criteria :" + intent.getSerializableExtra("Criteria"));

            Criteria criteria = (Criteria) intent.getSerializableExtra("Criteria");

            if (criteria != null) {
                mCallback.applyCriteria(criteria);
                Log.d(TAG, "onReceive:  applyCriteria");
            }
        }
    }

    public void setCallback(final ICustomListener callback) {
        mCallback = callback;
    }

    public interface ICustomListener {
        void applyCriteria(Criteria criteria);
    }
}
