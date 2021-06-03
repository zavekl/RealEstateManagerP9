package com.openclassrooms.realestatemanager.model;

import android.text.TextUtils;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by NIATEL Brice on 28/01/2021.
 */

//Permit to use complex object in room
public class Converters {
    @TypeConverter
    public String addressToString(Address address) {
        List<String> list = new ArrayList<>();
        list.add(address.getNumberStreet());
        list.add(address.getPostalCode());
        list.add(address.getTown());
        list.add(address.getLat());
        list.add(address.getLng());
        return TextUtils.join(",", list);
    }

    @TypeConverter
    public static Address stringToAddress(String s) {
        List<String> list = Arrays.asList(TextUtils.split(s, ","));
        return new Address(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
    }

    @TypeConverter
    public String listImageToString(List<String> list) {
        return TextUtils.join(",", list);
    }

    @TypeConverter
    public List<String> stringToListImage(String s) {
        return Arrays.asList(TextUtils.split(s, ","));
    }
}
