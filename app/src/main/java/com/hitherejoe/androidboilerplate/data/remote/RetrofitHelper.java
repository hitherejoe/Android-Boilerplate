package com.hitherejoe.androidboilerplate.data.remote;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RetrofitHelper {

    private static final String API_KEY = "6c69d2b4bc9d5ca502c674bbe7e2039c";
    private static final String PRIVATE_KEY = "873db370c65979ae06a902f569f469b99785db12";

    public AndroidBoilerplateService newAndroidBoilerplateService(final Context context) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(AndroidBoilerplateService.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestInterceptor.RequestFacade request) {
                        long stamp = System.currentTimeMillis();
                        request.addQueryParam("apikey", API_KEY);
                        request.addQueryParam("hash", md5(createHash(stamp)));
                        request.addQueryParam("ts", String.valueOf(stamp));
                    }
                })
                .build();
        return restAdapter.create(AndroidBoilerplateService.class);
    }

    private String createHash(long stamp) {
        return stamp + PRIVATE_KEY + API_KEY;
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
