package com.hitherejoe.androidboilerplate.injection.component;

import android.app.Application;

import com.hitherejoe.androidboilerplate.data.DataManager;
import com.hitherejoe.androidboilerplate.injection.module.ApplicationModule;
import com.hitherejoe.androidboilerplate.ui.activity.MainActivity;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    Application application();
    DataManager dataManager();
    Bus eventBus();
}