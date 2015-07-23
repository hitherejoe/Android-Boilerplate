package uk.co.ribot.androidboilerplate;

import android.database.Cursor;

import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.Db;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.util.DefaultConfig;
import uk.co.ribot.androidboilerplate.util.MockModelsUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

    private DatabaseHelper mDatabaseHelper;

    @Before
    public void setUp() {
        mDatabaseHelper = new DatabaseHelper(RuntimeEnvironment.application);
    }

    @Test
    public void shouldSetRibots() throws Exception {
        Ribot ribot1 = MockModelsUtil.createRibot();
        Ribot ribot2 = MockModelsUtil.createRibot();
        List<Ribot> ribots = Arrays.asList(ribot1, ribot2);

        TestSubscriber<Ribot> result = new TestSubscriber<>();
        mDatabaseHelper.setRibots(ribots).subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(ribots);

        Cursor cursor = mDatabaseHelper.getBriteDb()
                .query("SELECT * FROM " + Db.RibotsTable.TABLE_NAME);
        assertEquals(2, cursor.getCount());
        for (Ribot ribot : ribots) {
            cursor.moveToNext();
            assertEquals(ribot, Db.RibotsTable.parseCursor(cursor));
        }
    }

    @Test
    public void shouldGetRibots() throws Exception {
        Ribot ribot1 = MockModelsUtil.createRibot();
        Ribot ribot2 = MockModelsUtil.createRibot();
        List<Ribot> ribots = Arrays.asList(ribot1, ribot2);

        mDatabaseHelper.setRibots(ribots).subscribe();

        TestSubscriber<List<Ribot>> result = new TestSubscriber<>();
        mDatabaseHelper.getRibots().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(Collections.singletonList(ribots));
    }

}