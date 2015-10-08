package com.hitherejoe.androidboilerplate.data.remote;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class RetrofitHelper {

    public AndroidBoilerplateService newAndroidBoilerplateService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AndroidBoilerplateService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(AndroidBoilerplateService.class);
    }

}
