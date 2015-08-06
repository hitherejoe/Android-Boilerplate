package uk.co.ribot.androidboilerplate;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;
import uk.co.ribot.androidboilerplate.injection.component.ApplicationComponent;
import uk.co.ribot.androidboilerplate.injection.component.DaggerApplicationComponent;
import uk.co.ribot.androidboilerplate.injection.module.ApplicationModule;

public class BoilerplateApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            //TODO uncomment after replacing Crashlytics API Key in AndroidManifest.xml
            //Crashlytics.start(this);
        }

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static BoilerplateApplication get(Context context) {
        return (BoilerplateApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}

