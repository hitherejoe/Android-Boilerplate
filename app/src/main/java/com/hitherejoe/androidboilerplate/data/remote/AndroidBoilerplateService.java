package com.hitherejoe.androidboilerplate.data.remote;

import com.hitherejoe.androidboilerplate.data.model.Boilerplate;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface AndroidBoilerplateService {

    String ENDPOINT = "https://android.boilerplate.com/v0/";

    /**
     * Return a list of the boiler plates.
     */
    @GET("/boilerplates")
    Observable<List<Boilerplate>> getAndroidBoilerplates();

}
