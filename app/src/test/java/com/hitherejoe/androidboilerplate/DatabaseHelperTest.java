package com.hitherejoe.androidboilerplate;

import android.database.Cursor;

import com.hitherejoe.androidboilerplate.data.local.DatabaseHelper;
import com.hitherejoe.androidboilerplate.data.local.Db;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.util.DefaultConfig;
import com.hitherejoe.androidboilerplate.util.MockModelsUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

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
        int[] ids = RuntimeEnvironment.application.getResources().getIntArray(R.array.avengers);
        List<Character> characters = MockModelsUtil.createListOfMockCharacters(ids);

        TestSubscriber<Character> result = new TestSubscriber<>();
        mDatabaseHelper.setCharacters(characters).subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(characters);

        Cursor cursor = mDatabaseHelper.getBriteDb()
                .query("SELECT * FROM " + Db.CharacterTable.TABLE_NAME);
        assertEquals(20, cursor.getCount());
        for (Character ribot : characters) {
            cursor.moveToNext();
            assertEquals(ribot, Db.CharacterTable.parseCursor(cursor));
        }
    }

    @Test
    public void shouldGetRibots() throws Exception {
        int[] ids = RuntimeEnvironment.application.getResources().getIntArray(R.array.avengers);
        List<Character> characters = MockModelsUtil.createListOfMockCharacters(ids);

        mDatabaseHelper.setCharacters(characters).subscribe();

        TestSubscriber<List<Character>> result = new TestSubscriber<>();
        mDatabaseHelper.getCharacters().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(Collections.singletonList(characters));
    }
}