package com.example.pets.data.internal.di;

import com.example.pets.data.internal.Api;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final long TIMEOUT = 25L;
    private static final String API = "http://kot3.com";

    @Provides
    public Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    public Retrofit provideRetrofitBuilder(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API)
                .build();
    }

    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor logger) {
        return new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build();
    }

    @Provides
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logger;
    }
}
