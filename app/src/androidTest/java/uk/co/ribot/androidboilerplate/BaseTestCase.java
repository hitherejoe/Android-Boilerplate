package uk.co.ribot.androidboilerplate;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;

import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

public class BaseTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    protected RibotsService mMockRibotsService;

    public BaseTestCase(Class<T> cls) {
        super(cls);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
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

}