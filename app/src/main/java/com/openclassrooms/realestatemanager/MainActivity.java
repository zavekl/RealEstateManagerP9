package com.openclassrooms.realestatemanager;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = "MainActivity";

    private final String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        askPermissions();
    }

    //Set XML
    private void setView() {
        ViewPager mViewPager = findViewById(R.id.view_pager);
        TabLayout mTabLayout = findViewById(R.id.tab_layout);

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @AfterPermissionGranted(123)
    private void askPermissions() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            Log.d(TAG, "askPermissions: has permissions");
            setView();
        } else {
            Log.d(TAG, "askPermissions: hasn't permissions");
            EasyPermissions.requestPermissions(this, "We need permissions for the map.",
                    123, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: start");
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            Log.d(TAG, "onPermissionsGranted: has permissions");
            setView();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        boolean toast = false;
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Log.d(TAG, "onPermissionsDenied: permanently denied");
            new AppSettingsDialog.Builder(this).build().show();
            toast = true;
        }
        if (!toast) {
            Log.d(TAG, "onPermissionsDenied: denied");
            displayToastIfPermsDenied();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Log.d(TAG, "onActivityResult: request code is the same");
            if (displayToastIfPermsDenied()) {
                Log.d(TAG, "onActivityResult: set the view");
                setView();
            }
        }
    }

    //Toast if permissions denied
    private boolean displayToastIfPermsDenied() {
        if (EasyPermissions.somePermissionDenied(this, permissions)) {
            Utils.getEmojiByUnicode(0x26A0);
            Toast.makeText(this, (Utils.getEmojiByUnicode(0x26A0)) + "Warn This application will not work normally, please restart the application." +
                    (Utils.getEmojiByUnicode(0x26A0)), Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
}

