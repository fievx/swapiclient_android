package com.swapiclient.model.api_access;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.swapiclient.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jeremy on 17/11/2016.
 */
public class SwapiClient {
    private static SwapiService service;

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
            .build();

    public static Retrofit retrofit =
            new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

    public static SwapiService getService() {
        if (service == null) {
            service = retrofit.create(SwapiService.class);
        }
        return service;
    }
}
