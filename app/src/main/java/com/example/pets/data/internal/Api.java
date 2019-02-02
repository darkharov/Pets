package com.example.pets.data.internal;

import com.example.pets.core.entities.Pet;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/xim/api.php")
    Single<PetsResponse> pets(@Query("query") String criteria);


    class PetsResponse {
        public final List<Pet> data = Collections.emptyList();
    }
}

