package com.example.dogapp.model;

import com.google.gson.Gson;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogAPIService {
    private static final String BASE_URL = "https://raw.githubusercontent.com";
    private DogAPI api;

    public DogAPIService() {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(DogAPI.class);
    }

    public Single<List<DogBreed>> getDog () {
        return api.getDog();
    }
}
