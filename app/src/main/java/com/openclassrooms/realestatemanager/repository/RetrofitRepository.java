package com.openclassrooms.realestatemanager.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.openclassrooms.realestatemanager.model.projo.NearByPlaceResults;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by <NIATEL Brice> on <15/06/2020>.
 */
public class RetrofitRepository {
    private static final String TAG = "RetrofitRepository";

    private static final int RADIUS = 500;
    private static final boolean SENSOR = true;
    private static final String TYPESEARCH = "restaurant,amusement_park,aquarium,art_gallery,bar," +
            "cafe,casinohair_care,home_goods_store,library,meal_delivery,meal_takeaway,movie_theater" +
            ",museum,park,pharmacy,primary_school,post_office,school,secondary_school,supermarket," +
            "tourist_attraction,university,zoo";

    private final ApiGoogleMapRetrofit mApiService;

    public RetrofitRepository() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        // Build retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mApiService = retrofit.create(ApiGoogleMapRetrofit.class);
    }

    public LiveData<NearByPlaceResults> getPOIAroundUser(String latLng) {
        final MutableLiveData<NearByPlaceResults> liveData = new MutableLiveData<>();
        final Call<NearByPlaceResults> call = mApiService.getPOIAroundUser(latLng, RADIUS, TYPESEARCH, SENSOR, "AIzaSyAca9g8d5Zsg65NzlXcjGlIhup3ZP9Irv8");

        call.enqueue(new Callback<NearByPlaceResults>() {
            @Override
            public void onResponse(@NotNull Call<NearByPlaceResults> call, @NotNull Response<NearByPlaceResults> response) {
                liveData.setValue(response.body());
                Log.d(TAG, "onResponse: " + Objects.requireNonNull(response.body()).getResults());
            }

            @Override
            public void onFailure(@NotNull Call<NearByPlaceResults> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
        return liveData;
    }
}
