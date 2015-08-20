package com.hitherejoe.androidboilerplate.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit.RetrofitError;

public class NetworkUtil {

    /**
     * Returns true if the Throwable is an instance of RetrofitError with an
     * http status code equals to the given one.
     */
    public static boolean isHttpStatusCode(Throwable throwable, int statusCode) {
        if (RetrofitError.class.isInstance(throwable)) {
            RetrofitError retrofitError = (RetrofitError) throwable;
            if (retrofitError.getResponse() != null) {
                return retrofitError.getResponse().getStatus() == statusCode;
            }
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}