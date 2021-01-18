package com.openclassrooms.realestatemanager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MapFragment extends Fragment implements OnMapReadyCallback, EasyPermissions.PermissionCallbacks {
    private static final String TAG = "MapFragment";

    private final String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private FloatingActionButton mFab;

    private MapFragmentViewModel mViewModel;
    private MapView mMapView;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        mMapView = view.findViewById(R.id.mapView);
        mFab = view.findViewById(R.id.fab);

        //Create the map
        mMapView.onCreate(savedInstanceState);

        //Display the map immediately
        mMapView.onResume();

        //Set callback
        mMapView.getMapAsync(this);

        //Permissions
        askPermissions();

        return view;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean locationGranted = EasyPermissions.hasPermissions(requireContext(), permissions);
                if (!locationGranted) {
                    Log.d(TAG, "onClick: location denied : " + locationGranted);
                    askPermissions();
                } else {
                    Log.d(TAG, "onClick: location granted");
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) this, new ViewModelProvider.NewInstanceFactory()).get(MapFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

    @AfterPermissionGranted(123)
    private void askPermissions() {
        if (!EasyPermissions.hasPermissions(requireContext(), permissions)) {
            Log.d(TAG, "askPermissions: hasn't permissions");
            EasyPermissions.requestPermissions(this, requireContext().getResources().getString(R.string.permissions_denied),
                    123, permissions);
        } else {
            Log.d(TAG, "askPermissions: has permissions");
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
        if (EasyPermissions.hasPermissions(requireContext(), permissions)) {
            Log.d(TAG, "onPermissionsGranted: has permissions");
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Log.d(TAG, "onActivityResult: request code is the same");
            displayToastIfPermsDenied();
        }
    }

    //Toast if permissions denied
    private void displayToastIfPermsDenied() {
        if (EasyPermissions.somePermissionDenied(this, permissions)) {
            Utils.getEmojiByUnicode(0x26A0);
            Toast.makeText(requireContext(), (Utils.getEmojiByUnicode(0x26A0)) + requireContext().getResources().getString(R.string.permissions_toast_denied) +
                    (Utils.getEmojiByUnicode(0x26A0)), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}