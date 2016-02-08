package com.hitherejoe.androidboilerplate.data.remote;

import android.content.Context;

import com.hitherejoe.androidboilerplate.BuildConfig;
import com.hitherejoe.androidboilerplate.data.model.Character;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface AndroidBoilerplateService {

    String ENDPOINT = "http://swapi.co/api/";

    @GET("people/{personId}")
    Observable<Character> getCharacter(@Path("personId") int id);

    /********
     * Factory class that sets up a new boilerplate service
     *******/
    class Factory {

        public static AndroidBoilerplateService makeAndroidBoilerplateService(Context context) {
            OkHttpClient okHttpClient = new OkHttpClient();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                    : HttpLoggingInterceptor.Level.NONE);
//            okHttpClient.interceptors().add(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AndroidBoilerplateService.ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(AndroidBoilerplateService.class);
        }

    }
}
