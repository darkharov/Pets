package com.example.pets.data.internal;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/xim/api.php")
    Single<PetsResponse> pets(@Query("query") String criteria);


    class PetsResponse {

        public final List<Entry> data = Collections.emptyList();

        public static class Entry {
            public String title;
            public String url;
        }
    }
}
