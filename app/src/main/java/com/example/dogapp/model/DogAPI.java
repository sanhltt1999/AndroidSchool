package com.example.dogapp.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface DogAPI {
    @GET("DevTides/DogsApi/master/dogs.json")
    Single<List<DogBreed>> getDog();
}
