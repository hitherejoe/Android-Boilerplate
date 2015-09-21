package com.hitherejoe.androidboilerplate.data.remote;

import com.hitherejoe.androidboilerplate.data.model.Character;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface AndroidBoilerplateService {

    String ENDPOINT = "http://swapi.co/api/";

    @GET("people/{personId}")
    Observable<Character> getCharacter(@Path("personId") int id);

}
