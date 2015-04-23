package uk.co.ribot.androidboilerplate.data;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import uk.co.ribot.androidboilerplate.AndroidBoilerplateApplication;
import uk.co.ribot.androidboilerplate.BuildConfig;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.util.ComponentUtil;
import uk.co.ribot.androidboilerplate.util.NetworkUtil;

import rx.Observer;
import rx.Subscription;

public class SyncService extends Service {

    public static final String TAG = "SyncService";

    private Subscription mSubscription;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static boolean isRunning(Context context) {
        return ComponentUtil.isServiceRunning(context, SyncService.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        if (BuildConfig.DEBUG) Log.i(TAG, "Starting sync...");

        if (!NetworkUtil.isNetworkConnected(this)) {
            if (BuildConfig.DEBUG) Log.i(TAG, "Sync canceled, connection not available.");
            ComponentUtil.toggleComponent(this, SyncOnConnectionAvailable.class, true);
            stopSelf(startId);
            return START_NOT_STICKY;
        }

        DataManager dataManager =
                ((AndroidBoilerplateApplication) getApplicationContext()).getDataManager();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
        mSubscription = dataManager.syncRibots()
                .subscribeOn(dataManager.getScheduler())
                .subscribe(new Observer<Ribot>() {
                    @Override
                    public void onCompleted() {
                        if (BuildConfig.DEBUG) Log.i(TAG, "Synced successfully!");
                        stopSelf(startId);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG) Log.w(TAG, "Error syncing " + e);
                        stopSelf(startId);

                    }

                    @Override
                    public void onNext(Ribot ribot) {
                    }
                });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null) mSubscription.unsubscribe();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class SyncOnConnectionAvailable extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetworkUtil.isNetworkConnected(context)) {
                if (BuildConfig.DEBUG) {
                    Log.i(TAG, "Connection is now available, triggering sync...");
                }
                ComponentUtil.toggleComponent(context, this.getClass(), false);
                context.startService(getStartIntent(context));
            }
        }
    }

}