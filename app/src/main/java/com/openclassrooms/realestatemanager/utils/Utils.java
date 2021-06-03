package com.openclassrooms.realestatemanager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.openclassrooms.realestatemanager.BuildConfig;
import com.openclassrooms.realestatemanager.model.Address;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Philippe on 21/02/2018.
 */

public class Utils {
    private static final String TAG = "Utils";

    /**
     * Conversion d'un prix d'un bien immobilier (Dollars vers Euros)
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @param dollars
     * @return
     */
    public static int convertDollarToEuro(int dollars) {
        return (int) Math.round(dollars * 0.812);
    }

    //New method
    public static int convertEuroToDollar(int euros) {
        return (int) Math.round(euros * 1.212);
    }

    /**
     * Conversion de la date d'aujourd'hui en un format plus approprié
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @return
     */
    public static String getTodayDate1() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(new Date());
    }

    //New method
    public static String getTodayDate2() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss.SSS", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    /**
     * Vérification de la connexion réseau
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @param context
     * @return
     */
    public static Boolean isInternetAvailable1(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifi.isWifiEnabled();
    }

    //New method
    public static Boolean isInternetAvailable2(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    //Get Emoji by unicode
    public static String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    //Check if is the first run of the app
    public static Boolean checkFirstRun(Context context) {
        boolean result = false;
        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            result = false;

        } else if (savedVersionCode == DOESNT_EXIST) {
            result = true;

        } else if (currentVersionCode > savedVersionCode) {
            result = false;

        }
        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
        return result;
    }

    //Get Bitmap from drawable
    public static Bitmap getBitmap(int drawableRes, Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static boolean validateAddress(String s) {
        List<String> list = Arrays.asList(TextUtils.split(s, ","));
        Log.d(TAG, "validateAddress: " + list.size());
        return list.size() == 3;
    }

    public static Address stringToAddress(String s) {
        List<String> list = Arrays.asList(TextUtils.split(s, ","));
        Log.d(TAG, "stringToAdress: " + s);
        Log.d(TAG, "stringToAdress: " + list.toString());

        if (list.size() == 3) {
            return new Address(list.get(0), list.get(1), list.get(2));
        } else {
            return null;
        }
    }

    //Convert list of image to string with delimiter
    public String photoListToString(List<String> list) {
        return TextUtils.join(",", list);
    }

    //Convert string with delimiter to list of image
    public static List<String> stringToPhotoList(String s) {
        return Arrays.asList(TextUtils.split(s, ","));

    }
}