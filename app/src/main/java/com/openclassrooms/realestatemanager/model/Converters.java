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
    public String AddressToString(Address address) {
        List<String> list = new ArrayList<>();
        list.add(address.getNumber());
        list.add(address.getStreet());
        list.add(address.getPostalCode());
        list.add(address.getTown());
        return TextUtils.join(",", list);
    }

    @TypeConverter
    public Address StringToAdress(String s) {
        List<String> list = Arrays.asList(TextUtils.split(s, ","));
        return new Address(list.get(0), list.get(1), list.get(2), list.get(3));
    }

    @TypeConverter
    public String ListImageToString(List<String> list) {
        return TextUtils.join(",", list);
    }

    @TypeConverter
    public List<String> StringToListImage(String s) {
        return Arrays.asList(TextUtils.split(s, ","));
    }
}
