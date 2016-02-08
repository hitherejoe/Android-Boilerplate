package com.hitherejoe.androidboilerplate.injection.component;

import android.app.LauncherActivity;

import com.hitherejoe.androidboilerplate.injection.PerActivity;
import com.hitherejoe.androidboilerplate.injection.module.ActivityModule;
import com.hitherejoe.androidboilerplate.ui.activity.CharacterActivity;
import com.hitherejoe.androidboilerplate.ui.activity.DetailActivity;
import com.hitherejoe.androidboilerplate.ui.activity.MainActivity;
import com.hitherejoe.androidboilerplate.ui.fragment.DetailFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LauncherActivity launcherActivity);
    void inject(MainActivity mainActivity);
    void inject(CharacterActivity characterActivity);
    void inject(DetailActivity detailActivity);

    void inject(DetailFragment detailFragment);
}

