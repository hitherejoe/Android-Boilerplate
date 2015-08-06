package uk.co.ribot.androidboilerplate;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import uk.co.ribot.androidboilerplate.data.remote.RibotsService;

import uk.co.ribot.androidboilerplate.injection.component.ApplicationTestComponent;
import uk.co.ribot.androidboilerplate.injection.component.DaggerApplicationTestComponent;
import uk.co.ribot.androidboilerplate.injection.module.ApplicationTestModule;
import uk.co.ribot.androidboilerplate.util.TestDataManager;

public class BaseTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    protected TestDataManager mDataManager;
    protected RibotsService mMockRibotsService;

    public BaseTestCase(Class<T> cls) {
        super(cls);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        BoilerplateApplication application = BoilerplateApplication
                .get(getInstrumentation().getTargetContext());

        ApplicationTestComponent appTestComponent = DaggerApplicationTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(application))
                .build();
        application.setComponent(appTestComponent);

        mDataManager = (TestDataManager) appTestComponent.dataManager();
        mMockRibotsService = mDataManager.getRibotsService();

        mDataManager.getPreferencesHelper().clear();
        mDataManager.getDatabaseHelper().clearTables();
    }

}