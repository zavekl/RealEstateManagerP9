package com.openclassrooms.realestatemanager.utils;

import android.content.Context;
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
    private final AutoCompleteTextView mType;

    private final TextInputEditText mTIPrice;
    private final TextInputEditText mTIDescription;
    private final TextInputEditText mTINumberAndStreet;
    private final TextInputEditText mTIPostalNumber;
    private final TextInputEditText mTITown;
    private final TextInputEditText mTISurface;
    private final TextInputEditText mTIRoom;
    private final TextInputEditText mTIBedroom;
    private final TextInputEditText mTIBathroom;

    private final Context mContext;

    public TextInputUtils(AutoCompleteTextView mType, TextInputEditText mTIPrice, TextInputEditText mTIDescription,
                          TextInputEditText mTINumberAndStreet, TextInputEditText mTIPostalNumber, TextInputEditText mTITown,
                          TextInputEditText mTISurface, TextInputEditText mTIRoom, TextInputEditText mTIBedroom, TextInputEditText
                                  mTIBathroom, Context mContext) {
        this.mType = mType;
        this.mTIPrice = mTIPrice;
        this.mTIDescription = mTIDescription;
        this.mTINumberAndStreet = mTINumberAndStreet;
        this.mTIPostalNumber = mTIPostalNumber;
        this.mTITown = mTITown;
        this.mTISurface = mTISurface;
        this.mTIRoom = mTIRoom;
        this.mTIBedroom = mTIBedroom;
        this.mTIBathroom = mTIBathroom;
        this.mContext = mContext;
    }

    public boolean validateAllParameters() {
        return validateType() & validatePrice() & validateDescription() & validateNumberAndStreet() & validatePostalNumber() &
                validateTown() & validateSurface() & validateRoom() & validateBedroom() & validateBathroom();
    }

    public List<String> getItems() {
        return Arrays.asList("House", "Castle", "Mansion", "Duplex", "Apartment");
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

    private boolean validateNumberAndStreet() {
        String text = Objects.requireNonNull(mTINumberAndStreet.getText().toString().trim());
        if (text.isEmpty()) {
            mTINumberAndStreet.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mTINumberAndStreet.setError(null);
            return true;
        }
    }

    private boolean validatePostalNumber() {
        String text = Objects.requireNonNull(mTIPostalNumber.getText().toString().trim());
        if (text.isEmpty()) {
            mTIPostalNumber.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mTIPostalNumber.setError(null);
            return true;
        }
    }

    private boolean validateTown() {
        String text = Objects.requireNonNull(mTITown.getText().toString().trim());
        if (text.isEmpty()) {
            mTITown.setError(mContext.getString(R.string.field_empty));
            return false;
        } else {
            mTITown.setError(null);
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
