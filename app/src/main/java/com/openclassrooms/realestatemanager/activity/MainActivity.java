package com.openclassrooms.realestatemanager.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.ViewPagerAdapter;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.repository.InternalFilesRepository;
import com.openclassrooms.realestatemanager.utils.Utils;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        if (Utils.checkFirstRun(this)) {
            savePhotoInApp();
        }
        Log.d(TAG, "onCreate: end");
    }

    private void savePhotoInApp() {
        InternalFilesRepository internalFilesRepository = ((MyApplication) getApplication()).getContainerDependencies().getInternalFilesRepository();

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.house_one);
        if (internalFilesRepository.setFile("creationDb1", bitmap1)) {
            Toast.makeText(this, getResources().getText(R.string.insufficient_memory), Toast.LENGTH_LONG).show();
        }
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.house_two);
        if (internalFilesRepository.setFile("creationDb2", bitmap2)) {
            Toast.makeText(this, getResources().getText(R.string.insufficient_memory), Toast.LENGTH_LONG).show();
        }
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.house_three);
        if (internalFilesRepository.setFile("creationDb3", bitmap3)) {
            Toast.makeText(this, getResources().getText(R.string.insufficient_memory), Toast.LENGTH_LONG).show();
        }
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.house_four);
        if (internalFilesRepository.setFile("creationDb4", bitmap4)) {
            Toast.makeText(this, getResources().getText(R.string.insufficient_memory), Toast.LENGTH_LONG).show();
        }
        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.house_five);
        if (internalFilesRepository.setFile("creationDb5", bitmap5)) {
            Toast.makeText(this, getResources().getText(R.string.insufficient_memory), Toast.LENGTH_LONG).show();
        }
        Bitmap bitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.house_six);
        if (internalFilesRepository.setFile("creationDb6", bitmap6)) {
            Toast.makeText(this, getResources().getText(R.string.insufficient_memory), Toast.LENGTH_LONG).show();
        }
    }
}

