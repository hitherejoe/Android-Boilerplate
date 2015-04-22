package uk.co.ribot.androidboilerplate.data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.remote.RetrofitHelper;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import com.squareup.otto.Bus;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action0;
import rx.functions.Func1;

public class DataManager {

    private RibotsService mRibotsService;
    private DatabaseHelper mDatabaseHelper;
    private PreferencesHelper mPreferencesHelper;
    private Scheduler mScheduler;
    private Bus mBus;

    public DataManager(Context context, Scheduler scheduler) {
        mRibotsService = new RetrofitHelper().setupRibotsService();
        mDatabaseHelper = new DatabaseHelper(context);
        mPreferencesHelper = new PreferencesHelper(context);
        mBus = new Bus();
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

    public Bus getBus() {
        return mBus;
    }

    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                postEventSafely(event);
            }
        };
    }

    // Helper method to post an event from a different thread to the main one.
    private void postEventSafely(final Object event) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mBus.post(event);
            }
        });
    }

}
