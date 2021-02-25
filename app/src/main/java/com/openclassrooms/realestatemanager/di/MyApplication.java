package com.openclassrooms.realestatemanager.di;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

/**
 * Created by <NIATEL BRICE> on <16/04/2020>.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private ContainerDependencies containerDependencies;

    //Enable multidex for version <=19
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public ContainerDependencies getContainerDependencies() {
        if (containerDependencies == null) {
            containerDependencies = new ContainerDependencies(this);
            Log.d(TAG, "getContainerDependencies: ");
        }
        return containerDependencies;
    }
}
