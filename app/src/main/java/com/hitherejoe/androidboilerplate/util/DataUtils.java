package com.hitherejoe.androidboilerplate.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hitherejoe.androidboilerplate.data.model.ErrorResponse;

import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;

public class DataUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static Gson getGsonInstance() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSSz")
                .create();
    }

}
