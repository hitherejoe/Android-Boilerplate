package uk.co.ribot.androidboilerplate;


import android.database.Cursor;

import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.local.Db;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.util.DefaultConfig;
import uk.co.ribot.androidboilerplate.util.MockModelsUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DataManagerTest {

    private DataManager mDataManager;
    private RibotsService mMockRibotsService;

    @Before
    public void setUp() {
        mDataManager = new DataManager(RuntimeEnvironment.application, Schedulers.immediate());
        mMockRibotsService = mock(RibotsService.class);
        mDataManager.setRibotsService(mMockRibotsService);
    }

    @Test
    public void shouldSyncRibots() throws Exception {
        List<Ribot> ribots = Arrays.asList(MockModelsUtil.createRibot(),
                MockModelsUtil.createRibot());
        when(mMockRibotsService.getRibots())
                .thenReturn(Observable.just(ribots));

        TestSubscriber<Ribot> result = new TestSubscriber<>();
        mDataManager.syncRibots().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(ribots);

        Cursor cursor = mDataManager.getDatabaseHelper().getBriteDb()
                .query("SELECT * FROM " + Db.RibotsTable.TABLE_NAME);
        assertEquals(2, cursor.getCount());
        cursor.close();
    }


}
