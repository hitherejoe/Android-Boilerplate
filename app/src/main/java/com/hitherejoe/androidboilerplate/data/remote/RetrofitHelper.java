package com.hitherejoe.androidboilerplate.data.remote;

import com.google.gson.GsonBuilder;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RetrofitHelper {

    public AndroidBoilerplateService newAndroidBoilerplateService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(AndroidBoilerplateService.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestInterceptor.RequestFacade request) {
                        // If required...
                    }
                })
                .build();
        return restAdapter.create(AndroidBoilerplateService.class);
    }

}
