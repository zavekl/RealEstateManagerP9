package com.openclassrooms.realestatemanager.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.ViewPagerAdapter;
import com.openclassrooms.realestatemanager.di.MyApplication;
import com.openclassrooms.realestatemanager.fragment.AddRealEstateFragment;
import com.openclassrooms.realestatemanager.fragment.CriteriaFragment;
import com.openclassrooms.realestatemanager.fragment.DescriptionRealEstateFragment;

import java.util.Objects;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static ViewPager mViewPager;
    private static TabLayout mTabLayout;

    private TextView mResearchText;
    private ImageView mResearchButton;
    private FragmentContainerView mFragmentCV;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

        //Toolbar
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mResearchText = findViewById(R.id.tv_research);
        mResearchButton = findViewById(R.id.iv_arrow);
        mFragmentCV = findViewById(R.id.research_container);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        ((MyApplication) getApplication()).getContainerDependencies().getInternalFilesRepository().savePhotoInApp();

        //Set listener on search toolbar
        setCLickOnMenuToolbar();
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

        if (mResearchText.getVisibility() == View.VISIBLE) {
            hideResearchTextview();
        } else if (!(fragment instanceof DescriptionRealEstateFragment) & !(fragment instanceof AddRealEstateFragment)) {
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

    //Hide view pager
    private void hideResearchTextview() {
        mResearchText.setVisibility(View.GONE);
        mToolbar.setVisibility(View.VISIBLE);
    }

    //Display view pager
    private void revealResearchTextview() {
        mResearchText.setVisibility(View.VISIBLE);
        mToolbar.setVisibility(View.INVISIBLE);
        mResearchText.bringToFront();
    }

    //Create search menu in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Set listener on search toolbar to reveal the edit text if clicked
    private void setCLickOnMenuToolbar() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_restaurant) {
                    switch (mViewPager.getCurrentItem()) {
                        case 0: {
                            Log.d(TAG, "onMenuItemClick: fragment : MapFragment");
                            revealResearchTextview();
                            break;
                        }
                        case 1: {
                            Log.d(TAG, "onMenuItemClick: fragment : RVListRealEstateFragment");
                            revealResearchTextview();
                            break;
                        }
                    }
                }
                return true;
            }
        });
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
}

