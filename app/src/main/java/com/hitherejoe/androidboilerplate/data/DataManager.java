package com.hitherejoe.androidboilerplate.data;

import android.content.Context;

import com.hitherejoe.androidboilerplate.data.local.DatabaseHelper;
import com.hitherejoe.androidboilerplate.data.local.PreferencesHelper;
import com.hitherejoe.androidboilerplate.data.model.Boilerplate;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.data.remote.RetrofitHelper;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class DataManager {

    private AndroidBoilerplateService mAndroidBoilerplateService;
    private DatabaseHelper mDatabaseHelper;
    private PreferencesHelper mPreferencesHelper;
    private Scheduler mScheduler;

    public DataManager(Context context, Scheduler scheduler) {
        mAndroidBoilerplateService = new RetrofitHelper().setupHackerNewsService();
        mDatabaseHelper = new DatabaseHelper(context);
        mPreferencesHelper = new PreferencesHelper(context);
        mScheduler = scheduler;
    }

    public void setAndroidBoilerplateService(AndroidBoilerplateService androidBoilerplateService) {
        mAndroidBoilerplateService = androidBoilerplateService;
    }

    public void setScheduler(Scheduler scheduler) {
        mScheduler = scheduler;
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Scheduler getScheduler() {
        return mScheduler;
    }

    public Observable<Boilerplate> getAndroidBoilerplates() {
        return mAndroidBoilerplateService.getAndroidBoilerplates()
                .concatMap(new Func1<List<Boilerplate>, Observable<? extends Boilerplate>>() {
                    @Override
                    public Observable<? extends Boilerplate> call(List<Boilerplate> boilerplates) {
                        return Observable.from(boilerplates);
                    }
                });
    }

}
