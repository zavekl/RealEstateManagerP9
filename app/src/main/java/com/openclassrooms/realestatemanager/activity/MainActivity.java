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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.ViewPagerAdapter;
import com.openclassrooms.realestatemanager.fragment.AddRealEstateFragment;
import com.openclassrooms.realestatemanager.fragment.CriteriaFragment;
import com.openclassrooms.realestatemanager.fragment.DescriptionRealEstateFragment;
import com.openclassrooms.realestatemanager.fragment.SimulatorRealEstateLoanFragment;
import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.utils.CriteriaReceiver;
import com.openclassrooms.realestatemanager.utils.ToolbarReceiver;
import com.openclassrooms.realestatemanager.viewmodel.MainActivityViewModel;

import java.util.Objects;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;
import static com.openclassrooms.realestatemanager.adapter.ListRealEstateRVAdapter.BUNDLE_ID_DESCRIPTION;

public class MainActivity extends AppCompatActivity implements CriteriaReceiver.ICustomListener {
    private static final String TAG = "MainActivity";

    private static ConstraintLayout mConstraintLayout;

    private static ViewPager mViewPager;
    private static TabLayout mTabLayout;

    private static ImageButton mResearchButton;
    private ImageButton mStateResearchButton;
    private static FragmentContainerView mFragmentCV;
    private static FragmentContainerView mFragmentDescription;

    private MainActivityViewModel mViewModel;

    private CriteriaReceiver mReceiverCriteria;

    public static boolean mTabletMode = false;
    public static boolean mMapView = true;

    private final int MAP_VIEWPAGER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

        mConstraintLayout = findViewById(R.id.view_pager_constraint_layout);

        //ViewModel
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //Toolbar
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mResearchButton = findViewById(R.id.biv_criteria);
        mStateResearchButton = findViewById(R.id.biv_state_criteria);

        //Fragment Container
        mFragmentCV = findViewById(R.id.research_container);
        mFragmentDescription = findViewById(R.id.description_fragment);

        //ViewPager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //Set listener on search toolbar
        setOnClickMenuToolbar();
        setOnCLickResetCriteria();

        hideDescriptionFragment();
    }

    @Override
    public void onBackPressed() {
        Fragment fragmentID = getSupportFragmentManager().findFragmentById(R.id.description_fragment);

        if (fragmentID instanceof DescriptionRealEstateFragment) {
            Log.d(TAG, "onBackPressed: Description fragment is visible");

            if (mTabletMode && !mMapView) {
                if (mFragmentCV.getVisibility() != View.VISIBLE) {
                    Log.d(TAG, "onBackPressed: Description fragment : TABLET MODE");
                    if (mViewModel.getSharedPrefItemDescription() != null) {
                        long Id = Long.parseLong(mViewModel.getSharedPrefItemDescription());
                        if (!String.valueOf(Id).equals(fragmentID.getTag())) {
                            Log.d(TAG, "onBackPressed: Description fragment : replace desc fragment by first item");
                            DescriptionRealEstateFragment fragment = DescriptionRealEstateFragment.newInstance();

                            Bundle bundle = new Bundle();
                            bundle.putLong(BUNDLE_ID_DESCRIPTION, Id);

                            fragment.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.description_fragment, fragment, String.valueOf(Id))
                                    .commit();
                        } else {
                            Log.d(TAG, "onBackPressed: Description fragment : Already first item displayed then back on map view");
                            mViewPager.setCurrentItem(MAP_VIEWPAGER);
                            tabletModeMap();
                        }
                    }
                }
            } else {
                Log.d(TAG, "onBackPressed:");
                getSupportFragmentManager().beginTransaction().remove(fragmentID).commit();
                hideDescriptionFragment();
                displayViewPager();
                displayCriteriaButton();
            }
        }

        if (fragmentID instanceof AddRealEstateFragment) {
            Log.d(TAG, "onBackPressed: AddRealEstate fragment is visible");
            getSupportFragmentManager().beginTransaction().remove(fragmentID).commit();
            if (!mTabletMode) {
                hideDescriptionFragment();
            }
            displayViewPager();
        }

        if (fragmentID instanceof SimulatorRealEstateLoanFragment) {
            Log.d(TAG, "onBackPressed: SimulatorRealEstateLoanFragment fragment is visible");
            getSupportFragmentManager().beginTransaction().remove(fragmentID).commit();
        }


        if (mFragmentCV.getVisibility() == View.VISIBLE) {
            Log.d(TAG, "onBackPressed: criteria fragment set invisible");
            hideSearchFragment();
        } else if (!(fragmentID instanceof DescriptionRealEstateFragment) & !(fragmentID instanceof AddRealEstateFragment) && mFragmentCV.getVisibility() == View.INVISIBLE) {
            Log.d(TAG, "onBackPressed: MainActivity is visible");
            super.onBackPressed();
        }
    }

    //Manage screen of tablet mod
    private void manageTabletMod() {
        ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + position);
                switch (position) {
                    case 0:
                    default:
                        Log.d(TAG, "onPageScrolled: MAP");
                        tabletModeMap();
                        mViewPager.setCurrentItem(MAP_VIEWPAGER);
                        mMapView = true;
                        break;
                    case 1:
                        Log.d(TAG, "onPageScrolled: RV");
                        tabletModeRV();
                        mMapView = false;
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        mViewPager.addOnPageChangeListener(pageChangeListener);
        pageChangeListener.onPageSelected(MAP_VIEWPAGER);
    }

    //Hide
    public static void hideViewPager() {
        mTabLayout.setVisibility(View.INVISIBLE);
        mViewPager.setVisibility(View.INVISIBLE);
    }

    private void hideSearchFragment() {
        mFragmentCV.setVisibility(View.INVISIBLE);
    }

    public static void hideCriteriaButton() {
        mResearchButton.setVisibility(View.GONE);
    }

    private static void hideDescriptionFragment() {
        Log.d(TAG, "hideDescriptionFragment: ");
        mFragmentDescription.setVisibility(View.GONE);
    }

    //Display
    public static void displayViewPager() {
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
    }

    public static void displayCriteriaButton() {
        mResearchButton.setVisibility(View.VISIBLE);
    }

    public static void displayDescriptionFragment() {
        Log.d(TAG, "displayDescriptionFragment: ");
        mFragmentDescription.setVisibility(View.VISIBLE);
    }

    public static void displaySearchFragment() {
        mFragmentCV.setVisibility(View.VISIBLE);
        mFragmentCV.bringToFront();
    }

    //Create search menu in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Display full screen map if tablet mode
    private void tabletModeMap() {
        if (mTabletMode) {
            Log.d(TAG, "tabletModeMap: ");
            final ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mConstraintLayout.getLayoutParams();
            layoutParams.endToEnd = ConstraintLayout.LayoutParams.END;
            mConstraintLayout.setLayoutParams(layoutParams);
            hideDescriptionFragment();
        }
    }

    //Display half screen RV if tablet mode
    private void tabletModeRV() {
        if (mTabletMode) {
            Log.d(TAG, "tabletModeRV: ");
            final ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mConstraintLayout.getLayoutParams();
            layoutParams.endToEnd = R.id.guideline_main;
            mConstraintLayout.setLayoutParams(layoutParams);
            displayDescriptionFragment();
        }
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
            mViewModel.deleteSharedPrefCriteria();
            mStateResearchButton.setVisibility(View.INVISIBLE);
        }
    }

    //Initialize receivers for broadcast
    private void initReceiver() {
        Log.d(TAG, "initReceiver: start");
        mReceiverCriteria = new CriteriaReceiver();
        mReceiverCriteria.setCallback(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CriteriaReceiver.APPLY_CRITERIA);
        registerReceiver(mReceiverCriteria, intentFilter);
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
                mViewModel.deleteSharedPrefCriteria();
            }
        });
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        //If screen > 900 then tablet mod
        if (getResources().getConfiguration().screenWidthDp > 900) {
            Log.d(TAG, "onCreate: tablet mod");
            mTabletMode = true;
            hideDescriptionFragment();
            manageTabletMod();
            if (mViewPager.getCurrentItem() == 1) {
                tabletModeRV();
            } else {
                tabletModeMap();
            }
        } else {
            mTabletMode = false;
        }

        initReceiver();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        unregisterReceiver(mReceiverCriteria);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        mViewModel.deleteSharedPrefCriteria();
    }
}

