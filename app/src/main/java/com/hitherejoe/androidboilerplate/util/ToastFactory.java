package com.hitherejoe.androidboilerplate.util;

import android.content.Context;
import android.widget.Toast;

public class ToastFactory {

    public static Toast createToast(Context context, String message) {
        return Toast.makeText(context, message, Toast.LENGTH_SHORT);
    }
}
