package com.openclassrooms.realestatemanager.repository;

import com.openclassrooms.realestatemanager.model.Criteria;
import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NIATEL Brice on 24/03/2021.
 */
public class CriteriaRepo {

    private static final String TAG = "CriteriaRepo";

    private RealEstateRepository mRealEstateRepo;

    private Criteria mCriteria = new Criteria();

    private List<RealEstate> realEstatesList = new ArrayList<>();
    private List<RealEstate> resultFiltered = new ArrayList<>();

    public CriteriaRepo() {
    }

    private void getList() {

    }

    public Criteria getCriteria() {
        return mCriteria;
    }

    public void setCriteria(Criteria mCriteria) {
        this.mCriteria = mCriteria;
        getList();
    }

    public void setCiteriaText(String s) {
        mCriteria.setText(s);
    }

    public List<RealEstate> filterByAvailable(List<RealEstate> l) {
        return null;
    }
}
