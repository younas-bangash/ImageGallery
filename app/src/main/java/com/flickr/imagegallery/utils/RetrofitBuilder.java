package com.flickr.imagegallery.utils;

import android.content.Context;

import com.flickr.imagegallery.presenter.RetrofitInterface;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YounasBangash on 12/26/2017.
 */

public class RetrofitBuilder {
    private RetrofitInterface mApi;
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.flickr.com/";

    public RetrofitBuilder(Context context) {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        mApi = retrofit.create(RetrofitInterface.class);
    }

    public RetrofitInterface getApi(){
        if(mApi == null)
            mApi = retrofit.create(RetrofitInterface.class);
        return mApi;
    }
}
