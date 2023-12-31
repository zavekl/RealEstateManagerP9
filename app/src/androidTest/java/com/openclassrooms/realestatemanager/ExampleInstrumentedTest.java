package com.openclassrooms.realestatemanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.realestatemanager.model.Address;
import com.openclassrooms.realestatemanager.utils.Utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void stringToAddress() {
        String s = "76 New York Ave, Halesite, NY 11743, USA";
        Address expected = new Address("76 New York Ave", "Halesite", "NY 11743", "USA");
        assertEquals(expected.getNumberStreet(), Utils.stringToAddress(s).getNumberStreet());
        assertEquals(expected.getDistrict(), Utils.stringToAddress(s).getDistrict());
        assertEquals(expected.getPostalCode(), Utils.stringToAddress(s).getPostalCode());
        assertEquals(expected.getTown(), Utils.stringToAddress(s).getTown());
    }
}
