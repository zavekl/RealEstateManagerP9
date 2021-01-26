package com.openclassrooms.realestatemanager.di;

import android.app.Application;
import android.util.Log;

/**
 * Created by <NIATEL BRICE> on <16/04/2020>.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private ContainerDependencies containerDependencies;

    public ContainerDependencies getContainerDependencies() {
        if (containerDependencies == null) {
            containerDependencies = new ContainerDependencies(this);
            Log.d(TAG, "getContainerDependencies: ");
        }
        return containerDependencies;
    }
}
