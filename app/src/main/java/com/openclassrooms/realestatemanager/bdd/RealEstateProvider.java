package com.openclassrooms.realestatemanager.bdd;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.openclassrooms.realestatemanager.model.RealEstate;

public class RealEstateProvider extends ContentProvider {

    // FOR DATA
    public static final String AUTHORITY = "com.openclassrooms.realestatemanager.bdd";
    public static final String TABLE_NAME = RealEstate.class.getSimpleName();
    public static final Uri URI_ITEM = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (getContext() != null) {
            long userId = ContentUris.parseId(uri);
            final Cursor cursor = RealEstateDatabase.getInstance(getContext()).realEstateDao().getRealEstateByIdCursor(userId);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        } else {
            throw new IllegalArgumentException("Failed to query row for uri " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
