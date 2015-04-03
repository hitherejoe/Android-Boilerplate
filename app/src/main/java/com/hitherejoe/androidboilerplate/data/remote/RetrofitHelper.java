package com.hitherejoe.androidboilerplate.data.remote;

import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RetrofitHelper {

    public AndroidBoilerplateService setupHackerNewsService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(AndroidBoilerplateService.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        return restAdapter.create(AndroidBoilerplateService.class);
    }

}
