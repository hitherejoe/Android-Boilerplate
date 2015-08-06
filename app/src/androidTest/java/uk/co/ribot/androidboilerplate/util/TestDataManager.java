package uk.co.ribot.androidboilerplate.util;

import android.content.Context;

import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.injection.component.ApplicationTestComponent;
import uk.co.ribot.androidboilerplate.injection.component.DaggerDataManagerTestComponent;
import uk.co.ribot.androidboilerplate.injection.module.DataManagerTestModule;

/**
 * Extension of DataManager to be used on a testing environment.
 * It uses DataManagerTestComponent to inject dependencies that are different to the
 * normal runtime ones. e.g. mock objects etc.
 * It also exposes some helpers like the DatabaseHelper or the Retrofit service that are helpful
 * during testing.
 */
public class TestDataManager extends DataManager {

    public TestDataManager(Context context) {
        super(context);
    }

    @Override
    protected void injectDependencies(Context context) {
        ApplicationTestComponent applicationTestComponent = (ApplicationTestComponent)
                BoilerplateApplication.get(context).getComponent();
        DaggerDataManagerTestComponent.builder()
                .applicationTestComponent(applicationTestComponent)
                .dataManagerTestModule(new DataManagerTestModule(context))
                .build()
                .inject(this);
    }

    public RibotsService getRibotsService() {
        return mRibotsService;
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }
}
