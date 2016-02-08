package com.hitherejoe.androidboilerplate.injection.component;

import android.app.Application;
import android.content.Context;

import com.hitherejoe.androidboilerplate.AndroidBoilerplateApplication;
import com.hitherejoe.androidboilerplate.data.DataManager;
import com.hitherejoe.androidboilerplate.data.local.PreferencesHelper;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.injection.ApplicationContext;
import com.hitherejoe.androidboilerplate.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AndroidBoilerplateApplication androidBoilerplateApplication);

    @ApplicationContext
    Context context();
    Application application();
    AndroidBoilerplateService androidBoilerplateService();
    PreferencesHelper preferencesHelper();
    DataManager dataManager();

}
