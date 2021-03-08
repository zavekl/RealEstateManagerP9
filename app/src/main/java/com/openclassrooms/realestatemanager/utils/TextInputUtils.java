package com.openclassrooms.realestatemanager.utils;

import android.content.Context;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputEditText;
import com.openclassrooms.realestatemanager.R;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by NIATEL Brice on 11/02/2021.
 */
public class TextInputUtils {
    private static final String TAG = "TextInputUtils";

    private final AutoCompleteTextView mType;

    private final TextInputEditText mTIPrice;
    private final TextInputEditText mTIDescription;
    private final TextInputEditText mTISurface;
    private final TextInputEditText mTIRoom;
    private final TextInputEditText mTIBedroom;
    private final TextInputEditText mTIBathroom;
    private final TextInputEditText mTIAddress;

    private final Context mContext;

    public TextInputUtils(AutoCompleteTextView mType, TextInputEditText mTIPrice, TextInputEditText mTIDescription,
                          TextInputEditText mTISurface, TextInputEditText mTIRoom, TextInputEditText mTIBedroom, TextInputEditText
                                  mTIBathroom, TextInputEditText mTIAddress, Context mContext) {
        this.mType = mType;
        this.mTIPrice = mTIPrice;
        this.mTIDescription = mTIDescription;
        this.mTISurface = mTISurface;
        this.mTIRoom = mTIRoom;
        this.mTIBedroom = mTIBedroom;
        this.mTIBathroom = mTIBathroom;
        this.mTIAddress = mTIAddress;

        this.mContext = mContext;
    }

    public boolean validateAllParameters() {
        return validateType() & validatePrice() & validateDescription() & validateSurface() & validateRoom() & validateBedroom() & validateBathroom() & validateAddress();
    }

    public List<String> getItems() {
        return Arrays.asList("House", "Castle", "Mansion", "Duplex", "Apartment");
    }

    private boolean validateAddress() {
        String text = Objects.requireNonNull(mTIAddress.getText().toString());
        Log.d(TAG, "validateAddress: " + mTIAddress.getText().toString());
        if (text.isEmpty()) {
            Log.d(TAG, "validateAddress: empty");
            mTIAddress.setError(mContext.getString(R.string.field_empty));
            return false;
        }
        if (!Utils.validateAddress(text)) {
            Log.d(TAG, "validateAddress: incorrect address");
            mTIAddress.setError(mContext.getString(R.string.incorrect_address));
            return false;
        } else {
            Log.d(TAG, "validateAddress: good");
            mTIAddress.setError(null);
            return true;
        }
    }

    private boolean validateType() {
        String text = Objects.requireNonNull(mType.getText().toString().trim());
        if (text.isEmpty()) {
            mType.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mType.setError(null);
            return true;
        }
    }

    private boolean validatePrice() {
        String text = Objects.requireNonNull(mTIPrice.getText().toString().trim());
        if (text.isEmpty()) {
            mTIPrice.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mTIPrice.setError(null);
            return true;
        }
    }

    private boolean validateDescription() {
        String text = Objects.requireNonNull(mTIDescription.getText().toString().trim());
        if (text.isEmpty()) {
            mTIDescription.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mTIDescription.setError(null);
            return true;
        }
    }

    private boolean validateSurface() {
        String text = Objects.requireNonNull(mTISurface.getText().toString().trim());
        if (text.isEmpty()) {
            mTISurface.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mTISurface.setError(null);
            return true;
        }
    }

    private boolean validateRoom() {
        String text = Objects.requireNonNull(mTIRoom.getText().toString().trim());
        if (text.isEmpty()) {
            mTIRoom.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mTIRoom.setError(null);
            return true;
        }
    }

    private boolean validateBedroom() {
        String text = Objects.requireNonNull(mTIBedroom.getText().toString().trim());
        if (text.isEmpty()) {
            mTIBedroom.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mTIBedroom.setError(null);
            return true;
        }
    }

    private boolean validateBathroom() {
        String text = Objects.requireNonNull(mTIBathroom.getText().toString().trim());
        if (text.isEmpty()) {
            mTIBathroom.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mTIBathroom.setError(null);
            return true;
        }
    }
}
