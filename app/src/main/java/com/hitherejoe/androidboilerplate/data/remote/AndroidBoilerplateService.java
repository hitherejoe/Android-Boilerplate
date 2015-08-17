package com.hitherejoe.androidboilerplate.data.remote;

import com.hitherejoe.androidboilerplate.data.model.Character;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface AndroidBoilerplateService {

    String ENDPOINT = "http://gateway.marvel.com:80/v1/public";

    @GET("/characters/{characterId}")
    Observable<CharacterResponse> getCharacter(@Path("characterId") int id);

    class CharacterResponse {
        public Data data;
    }

    class Data {
        public List<Character> results;
    }
}
