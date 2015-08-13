package com.hitherejoe.androidboilerplate.data.remote;

import com.hitherejoe.androidboilerplate.data.model.Character;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface AndroidBoilerplateService {

    String ENDPOINT = "http://gateway.marvel.com:80/v1/public";

    /**
     * Return a list of the boiler plates.
     */
    @GET("/characters")
    Observable<CharacterResponse> getCharacters(@Query("limit") int limit);

    class CharacterResponse {
        public Data data;
    }

    class Data {
        public List<Character> results;
    }
}
