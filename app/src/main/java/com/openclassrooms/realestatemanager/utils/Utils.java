package com.openclassrooms.realestatemanager.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.openclassrooms.realestatemanager.model.Address;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Philippe on 21/02/2018.
 */

public class Utils {
    private static final String TAG = "Utils";

    /**
     * Conversion d'un prix d'un bien immobilier (Dollars vers Euros)
     * NOTE : NE PAS SUPPRIMER, A MONTRER DURANT LA SOUTENANCE
     *
     * @param dollars the type of money to convert
     * @return Euro
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
     * @return date in format yyyy/MM/dd
     */
    public static String getTodayDate1() {
        @SuppressLint("SimpleDateFormat")
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
     * @param context need for getSystemService method
     * @return if the wifi is enable
     */
    public static Boolean isInternetAvailable1(Context context) {
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
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

    //Get Bitmap from drawable
    public static Bitmap getBitmap(int drawableRes, Context context) {
        final Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
        final Canvas canvas = new Canvas();
        Bitmap bitmap;
        if (drawable != null) {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            canvas.setBitmap(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);

            return bitmap;
        } else {
            return null;
        }
    }

    public static boolean validateAddress(String s) {
        final Pattern p = Pattern.compile("\\d\\w{1,5}(\\s\\D+)+,(\\s\\D+)+,\\s\\w+\\s?\\d{0,5},\\s\\D+");
        final Matcher matcher = p.matcher(s);
        return matcher.find();
    }

    public static Address stringToAddress(String s) {
        List<String> list = Arrays.asList(TextUtils.split(s, ","));
        Log.d(TAG, "stringToAdress: " + s);
        Log.d(TAG, "stringToAdress: " + list.toString());

        if (list.size() == 4) {
            Log.d(TAG, "stringToAddress: list");
            return new Address(list.get(0), list.get(1).trim(), list.get(2).trim(), list.get(3).trim());
        } else {
            return null;
        }
    }

    //Convert string with delimiter to list of image
    public static List<String> stringToPhotoList(String s) {
        return Arrays.asList(TextUtils.split(s, ","));
    }
}