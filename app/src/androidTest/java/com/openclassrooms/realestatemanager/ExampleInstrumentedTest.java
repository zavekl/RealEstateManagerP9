package com.openclassrooms.realestatemanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.realestatemanager.activity.MainActivity;
import com.openclassrooms.realestatemanager.model.Utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule(MainActivity.class);

    @Test
    public void connectivitySuccess() {
        Context mockContext = Mockito.mock(Context.class);
        ConnectivityManager mockConnectivityManager = Mockito.mock(ConnectivityManager.class);
        NetworkInfo mockNetworkInfo = Mockito.mock(NetworkInfo.class);
        when(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockConnectivityManager);
        when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isConnected()).thenReturn(true);

        boolean result1 = Utils.isInternetAvailable2(mockContext);
        assertTrue("Connectivity available", result1);
    }

    @Test
    public void connectivityFail() {
        Context mockContext = Mockito.mock(Context.class);
        ConnectivityManager mockConnectivityManager = Mockito.mock(ConnectivityManager.class);
        NetworkInfo mockNetworkInfo = Mockito.mock(NetworkInfo.class);
        when(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockConnectivityManager);
        when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
        when(mockNetworkInfo.isConnected()).thenReturn(false);

        boolean result2 = Utils.isInternetAvailable2(mockContext);
        assertFalse("Connectivity available", result2);
    }
}
