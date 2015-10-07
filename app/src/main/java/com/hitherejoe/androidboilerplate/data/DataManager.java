package com.hitherejoe.androidboilerplate.data;

import android.content.Context;

import com.hitherejoe.androidboilerplate.AndroidBoilerplateApplication;
import com.hitherejoe.androidboilerplate.data.local.DatabaseHelper;
import com.hitherejoe.androidboilerplate.data.local.PreferencesHelper;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.injection.component.DaggerDataManagerComponent;
import com.hitherejoe.androidboilerplate.injection.module.DataManagerModule;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class DataManager {

    @Inject protected AndroidBoilerplateService mAndroidBoilerplateService;
    @Inject protected DatabaseHelper mDatabaseHelper;
    @Inject protected PreferencesHelper mPreferencesHelper;
    @Inject protected Scheduler mSubscribeScheduler;
    @Inject protected Bus mEventBus;

    public DataManager(Context context) {
        injectDependencies(context);
    }

    /* This constructor is provided so we can set up a DataManager with mocks from unit test.
     * At the moment this is not possible to do with Dagger because the Gradle APT plugin doesn't
     * work for the unit test variant, plus Dagger 2 doesn't provide a nice way of overriding
     * modules */
    public DataManager(AndroidBoilerplateService watchTowerService,
                       DatabaseHelper databaseHelper,
                       Bus eventBus,
                       PreferencesHelper preferencesHelper,
                       Scheduler subscribeScheduler) {
        mAndroidBoilerplateService = watchTowerService;
        mDatabaseHelper = databaseHelper;
        mEventBus = eventBus;
        mPreferencesHelper = preferencesHelper;
        mSubscribeScheduler = subscribeScheduler;
    }

    protected void injectDependencies(Context context) {
        DaggerDataManagerComponent.builder()
                .applicationComponent(AndroidBoilerplateApplication.get(context).getComponent())
                .dataManagerModule(new DataManagerModule(context))
                .build()
                .inject(this);
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Scheduler getSubscribeScheduler() {
        return mSubscribeScheduler;
    }

    public Observable<Character> syncCharacters(int[] ids) {
        return getCharacters(ids).toList().concatMap(new Func1<List<Character>, Observable<? extends Character>>() {
            @Override
            public Observable<? extends Character> call(List<Character> characters) {
                return mDatabaseHelper.setCharacters(characters);
            }
        });
    }

    public Observable<Character> getCharacters(int[] ids) {
        List<Integer> characterIds = new ArrayList<>(ids.length);
        for (int id : ids) characterIds.add(id);
        return Observable.from(characterIds).concatMap(new Func1<Integer, Observable<Character>>() {
            @Override
            public Observable<Character> call(Integer integer) {
                return mAndroidBoilerplateService.getCharacter(integer);
            }
        });
    }

    public Observable<Character> loadCharacters() {
        return mDatabaseHelper.getCharacters().concatMap(new Func1<List<Character>, Observable<? extends Character>>() {
            @Override
            public Observable<? extends Character> call(List<Character> characters) {
                return Observable.from(characters);
            }
        }).concatMap(new Func1<Character, Observable<? extends Character>>() {
            @Override
            public Observable<? extends Character> call(Character character) {
                return Observable.just(character);
            }
        }).distinct();
    }

}
