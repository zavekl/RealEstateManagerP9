package com.openclassrooms.realestatemanager.repository;

import com.openclassrooms.realestatemanager.model.projo.NearByPlaceResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by <NIATEL Brice> on <15/05/2020>.
 */
interface ApiGoogleMapRetrofit {
    //Get the list of poi near the realestate
    @GET("maps/api/place/nearbysearch/json?")
    Call<NearByPlaceResults> getPOIAroundRealestate(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type_search,
            @Query("sensor") boolean isSensor,
            @Query("key") String keyAPI
    );
}

