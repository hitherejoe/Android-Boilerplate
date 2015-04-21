package com.hitherejoe.androidboilerplate.data;

import android.content.Context;

import com.hitherejoe.androidboilerplate.data.local.DatabaseHelper;
import com.hitherejoe.androidboilerplate.data.local.PreferencesHelper;
import com.hitherejoe.androidboilerplate.data.model.Ribot;
import com.hitherejoe.androidboilerplate.data.remote.RetrofitHelper;
import com.hitherejoe.androidboilerplate.data.remote.RibotsService;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class DataManager {

    private RibotsService mRibotsService;
    private DatabaseHelper mDatabaseHelper;
    private PreferencesHelper mPreferencesHelper;
    private Scheduler mScheduler;

    public DataManager(Context context, Scheduler scheduler) {
        mRibotsService = new RetrofitHelper().setupRibotsService();
        mDatabaseHelper = new DatabaseHelper(context);
        mPreferencesHelper = new PreferencesHelper(context);
        mScheduler = scheduler;
    }

    public void setRibotsService(RibotsService ribotsService) {
        mRibotsService = ribotsService;
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

    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return mDatabaseHelper.saveRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

}
