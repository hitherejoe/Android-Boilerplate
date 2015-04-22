package uk.co.ribot.androidboilerplate;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import uk.co.ribot.androidboilerplate.AndroidBoilerplateApplication;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class BaseTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    protected RibotsService mMockRibotsService;

    public BaseTestCase(Class<T> cls) {
        super(cls);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        // Injecting the Instrumentation instance is required
        // for your test to run with AndroidJUnitRunner.
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        DataManager dataManager = AndroidBoilerplateApplication.get().getDataManager();
        //Mock the API services so tests don't rely on network connection
        mMockRibotsService = mock(RibotsService.class);
        dataManager.setRibotsService(mMockRibotsService);
        //Data manager Observables run in same thread as the tests
        dataManager.setScheduler(Schedulers.immediate());
        //Clear data
        dataManager.getPreferencesHelper().clear();
        dataManager.getDatabaseHelper().clearTables().subscribe();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

}