package uk.co.ribot.androidboilerplate.data.remote;

import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RetrofitHelper {

    public RibotsService setupRibotsService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RibotsService.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();
        return restAdapter.create(RibotsService.class);
    }

}
