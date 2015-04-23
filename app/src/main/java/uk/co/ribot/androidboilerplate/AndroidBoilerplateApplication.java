package uk.co.ribot.androidboilerplate;

import android.app.Application;

import uk.co.ribot.androidboilerplate.data.DataManager;

import rx.schedulers.Schedulers;

public class AndroidBoilerplateApplication extends Application {

    private static AndroidBoilerplateApplication sAndroidBoilerplateApplication;
    private DataManager mDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sAndroidBoilerplateApplication = this;
        mDataManager = new DataManager(this, Schedulers.io());
    }

    @Override
    public void onTerminate() {
        sAndroidBoilerplateApplication = null;
        super.onTerminate();
    }

    public static AndroidBoilerplateApplication get() {
        return sAndroidBoilerplateApplication;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

}

