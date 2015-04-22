package com.hitherejoe.androidboilerplate;


import android.database.Cursor;

import com.hitherejoe.androidboilerplate.data.DataManager;
import com.hitherejoe.androidboilerplate.data.local.Db;
import com.hitherejoe.androidboilerplate.data.model.Boilerplate;
import com.hitherejoe.androidboilerplate.data.model.Ribot;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.data.remote.RibotsService;
import com.hitherejoe.androidboilerplate.util.DefaultConfig;
import com.hitherejoe.androidboilerplate.util.MockModelsUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = DefaultConfig.EMULATE_SDK)
public class DataManagerTest {

    private DataManager mDataManager;
    private RibotsService mMockRibotsService;

    @Before
    public void setUp() {
        mDataManager = new DataManager(Robolectric.application, Schedulers.immediate());
        mMockRibotsService = mock(RibotsService.class);
        mDataManager.setRibotsService(mMockRibotsService);
    }

    @Test
    public void shouldSyncRibots() throws Exception {
        List<Ribot> ribots = Arrays.asList(MockModelsUtil.createRibot(), MockModelsUtil.createRibot());
        when(mMockRibotsService.getRibots())
                .thenReturn(Observable.just(ribots));

        TestSubscriber<Ribot> result = new TestSubscriber<>();
        mDataManager.syncRibots().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(ribots);

        Cursor cursor = mDataManager.getDatabaseHelper().getDb()
                .query("SELECT * FROM " + Db.RibotsTable.TABLE_NAME);
        assertEquals(2, cursor.getCount());
        cursor.close();
    }


}
