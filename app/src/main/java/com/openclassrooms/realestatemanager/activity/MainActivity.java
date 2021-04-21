package com.openclassrooms.realestatemanager.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.ViewPagerAdapter;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.fragment.AddRealEstateFragment;
import com.openclassrooms.realestatemanager.fragment.CriteriaFragment;
import com.openclassrooms.realestatemanager.fragment.DescriptionRealEstateFragment;
import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.utils.CriteriaReceiver;
import com.openclassrooms.realestatemanager.utils.ToolbarReceiver;
import com.openclassrooms.realestatemanager.viewmodel.MainActivityViewModel;

import java.util.Objects;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity implements CriteriaReceiver.ICustomListener {
    private static final String TAG = "MainActivity";

    private static ViewPager mViewPager;
    private static TabLayout mTabLayout;

    private ImageButton mResearchButton;
    private ImageButton mStateResearchButton;
    private FragmentContainerView mFragmentCV;

    private MainActivityViewModel mViewModel;

    private CriteriaReceiver mReceiverCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

        //ViewModel
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //Toolbar
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mResearchButton = findViewById(R.id.biv_criteria);
        mStateResearchButton = findViewById(R.id.biv_state_criteria);
        mFragmentCV = findViewById(R.id.research_container);

        //ViewPager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //Set photo
        ((MyApplication) getApplication()).getContainerDependencies().getInternalFilesRepository().savePhotoInApp();

        //Set listener on search toolbar
        setOnClickMenuToolbar();
        setOnCLickResetCriteria();
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

        if (mFragmentCV.getVisibility() == View.VISIBLE) {
            hideSearchFragment();
        } else if (!(fragment instanceof DescriptionRealEstateFragment) & !(fragment instanceof AddRealEstateFragment) && mFragmentCV.getVisibility() == View.INVISIBLE) {
            Log.d(TAG, "onBackPressed: MainActivity is visible");
            super.onBackPressed();
        }
    }

    //Hide view pager
    public static void hideViewPager() {
        mTabLayout.setVisibility(View.INVISIBLE);
        mViewPager.setVisibility(View.INVISIBLE);
    }

    //Display view pager
    public static void revealViewPager() {
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
    }

    private void hideSearchFragment() {
        mFragmentCV.setVisibility(View.INVISIBLE);
    }

    //Create search menu in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Set listener on search toolbar to reveal the edit text if clicked
    private void setOnClickMenuToolbar() {
        mResearchButton.bringToFront();
        mStateResearchButton.bringToFront();
        mStateResearchButton.setVisibility(View.INVISIBLE);

        mResearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentCV.getVisibility() == View.INVISIBLE) {
                    Log.d(TAG, "onClick: display criteria fragment");
                    mFragmentCV.setVisibility(View.VISIBLE);
                    mFragmentCV.bringToFront();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.research_container, CriteriaFragment.newInstance())
                            .commit();
                } else {
                    Log.d(TAG, "onClick: hide criteria fragment");
                    mFragmentCV.setVisibility(View.INVISIBLE);
                    getSupportFragmentManager().beginTransaction()
                            .remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.research_container)))
                            .commit();
                }
            }
        });
    }

    //Change visibility of mStateResearchButton when there is criteria selected or not
    @Override
    public void applyCriteria(Criteria criteria) {
        Log.d(TAG, "applyCriteria: ");
        if (mViewModel.isCriteria()) {
            Log.d(TAG, "applyCriteria: in if");
            mStateResearchButton.setVisibility(View.VISIBLE);
        } else {
            mViewModel.deleteSharedPref();
            mStateResearchButton.setVisibility(View.INVISIBLE);
        }
    }


    //Initialize receivers for broadcast
    private void initReceiver() {
        Log.d(TAG, "initReceiver: start");
        mReceiverCriteria = new CriteriaReceiver();
        mReceiverCriteria.setCallback(this);
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(CriteriaReceiver.APPLY_CRITERIA);
        registerReceiver(mReceiverCriteria, intentFilter1);
        Log.d(TAG, "initReceiver: end");
    }

    //Send broadcast when button is clicked
    private void setOnCLickResetCriteria() {
        mStateResearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: setOnCLickResetCriteria");
                //Send broadcast with the criteria object
                Intent intent = new Intent();
                intent.setAction(ToolbarReceiver.APPLY_RESET_CRITERIA);
                sendBroadcast(intent);

                mStateResearchButton.setVisibility(View.INVISIBLE);

                //Delete shared pref
                mViewModel.deleteSharedPref();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiverCriteria);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.deleteSharedPref();
    }
}

