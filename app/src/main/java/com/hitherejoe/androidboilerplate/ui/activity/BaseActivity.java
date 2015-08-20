package com.hitherejoe.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hitherejoe.androidboilerplate.AndroidBoilerplateApplication;
import com.hitherejoe.androidboilerplate.injection.component.ApplicationComponent;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent applicationComponent() {
        return AndroidBoilerplateApplication.get(this).getComponent();
    }

}
