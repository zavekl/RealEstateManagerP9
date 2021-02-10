package com.openclassrooms.realestatemanager.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.ViewPagerAdapter;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.fragment.AddRealEstateFragment;
import com.openclassrooms.realestatemanager.fragment.DescriptionRealEstateFragment;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static ViewPager mViewPager;
    private static TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        ((MyApplication) getApplication()).getContainerDependencies().getInternalFilesRepository().savePhotoInApp();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.description_fragment);

        if (fragment instanceof DescriptionRealEstateFragment) {
            Log.d(TAG, "onBackPressed: Description fragment is visible");
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            revealViewPager();
        }

        if (fragment instanceof AddRealEstateFragment) {
            Log.d(TAG, "onBackPressed: AddRealEstate fragment is visible");
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            revealViewPager();
        }

        if (!(fragment instanceof DescriptionRealEstateFragment) & !(fragment instanceof AddRealEstateFragment)) {
            Log.d(TAG, "onBackPressed: MainActivity is visible");
            super.onBackPressed();
        }
    }

    public static void hideViewPager() {
        mTabLayout.setVisibility(View.INVISIBLE);
        mViewPager.setVisibility(View.INVISIBLE);
    }

    private void revealViewPager() {
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
    }
}

