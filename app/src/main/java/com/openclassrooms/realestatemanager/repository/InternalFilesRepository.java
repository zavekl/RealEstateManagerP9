package com.openclassrooms.realestatemanager.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by NIATEL Brice on 22/01/2021.
 */
public class InternalFilesRepository {
    private static final String TAG = "InternalFilesRepository";

    public static final int MEMORY = 28311552;

    private final Context mContext;

    public InternalFilesRepository(Context mContext) {
        this.mContext = mContext;
    }

    private boolean checkSpaceMemory() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize, availableBlocks;
        blockSize = stat.getBlockSizeLong();
        availableBlocks = stat.getAvailableBlocksLong();

        Log.d(TAG, "checkSpaceMemory: " + availableBlocks * blockSize + " octet left");
        return availableBlocks * blockSize > MEMORY;
    }

    public boolean setFile(String name, Bitmap bitmapImage) {
        boolean result;
        if (checkSpaceMemory()) {
            result = false;
            File directory = mContext.getDir("imageDir", Context.MODE_PRIVATE); // path to /data/data/yourapp/app_data/imageDir
            File mypath = new File(directory, name + ".jpg");

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mypath);
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "setFile: ", e);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                        Log.d(TAG, "setFile: content writed in file successfully");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "setFile: ", e);
                    }
                } else {
                    Log.d(TAG, "setFile: Failed to write");
                }
            }
        } else {
            result = true;
        }
        return result;
    }

    public Bitmap getFile(String name) {
        File directory = mContext.getDir("imageDir", Context.MODE_PRIVATE); // path to /data/data/yourapp/app_data/imageDir
        File mypath = new File(directory, name + ".jpg");
        try {
            Log.d(TAG, "getFile: content get successfully ");
            return BitmapFactory.decodeStream(new FileInputStream(mypath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "getFile: ", e);
        }
        return null;
    }

    public void savePhotoInApp() {
        if (Utils.checkFirstRun(mContext)) {
            boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false, b6 = false;
            Bitmap bitmap1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.house_one);
            if (setFile("creationDb1", bitmap1)) {
                b1 = true;
            }
            Bitmap bitmap2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.house_two);
            if (setFile("creationDb2", bitmap2)) {
                b2 = true;
            }
            Bitmap bitmap3 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.house_three);
            if (setFile("creationDb3", bitmap3)) {
                b3 = true;
            }
            Bitmap bitmap4 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.house_four);
            if (setFile("creationDb4", bitmap4)) {
                b4 = true;
            }
            Bitmap bitmap5 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.house_five);
            if (setFile("creationDb5", bitmap5)) {
                b5 = true;
            }
            Bitmap bitmap6 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.house_six);
            if (setFile("creationDb6", bitmap6)) {
                b6 = true;
            }
            if (!b1 && !b2 && !b3 && !b4 && !b5 && !b6) {
                Log.d(TAG, "savePhotoInApp: all saved");
            } else {
                Log.d(TAG, "savePhotoInApp: not enough space");
            }

        } else {
            Log.d(TAG, "savePhotoInApp: don't need to save");
        }
    }
}
