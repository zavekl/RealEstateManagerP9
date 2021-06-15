package com.openclassrooms.realestatemanager.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by NIATEL Brice on 05/04/2021.
 */
public class ToolbarReceiver extends BroadcastReceiver {
    private static final String TAG = "ToolbarReceiver";

    ToolbarReceiver.ICustomListener mCallback = null;

    public static final String APPLY_RESET_CRITERIA = "APPLY_RESET_CRITERIA";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null) {
            if (intent.getAction().equals(APPLY_RESET_CRITERIA)) {
                Log.d(TAG, "onReceive: ");
                mCallback.applyResetCriteria();
            }
        }
    }

    public void setCallback(final ToolbarReceiver.ICustomListener callback) {
        mCallback = callback;
    }

    public interface ICustomListener {
        void applyResetCriteria();
    }
}
