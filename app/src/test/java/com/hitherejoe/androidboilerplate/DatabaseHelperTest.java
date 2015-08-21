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
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collections;
import java.util.List;

import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class DatabaseHelperTest {

    private DatabaseHelper mDatabaseHelper;

    @Before
    public void setUp() {
        mDatabaseHelper = new DatabaseHelper(RuntimeEnvironment.application);
        mDatabaseHelper.clearTables().subscribe();
    }

    @Test
    public void shouldSetCharacters() throws Exception {
        List<Character> characters = MockModelsUtil.createListOfMockCharacters(5);

        TestSubscriber<Character> result = new TestSubscriber<>();
        mDatabaseHelper.setCharacters(characters).subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(characters);

        Cursor cursor = mDatabaseHelper.getBriteDb()
                .query("SELECT * FROM " + Db.CharacterTable.TABLE_NAME);
        assertEquals(5, cursor.getCount());
        for (Character character : characters) {
            cursor.moveToNext();
            assertEquals(character, Db.CharacterTable.parseCursor(cursor));
        }
    }

    @Test
    public void shouldGetCharacters() throws Exception {
        List<Character> characters = MockModelsUtil.createListOfMockCharacters(5);

        mDatabaseHelper.setCharacters(characters).subscribe();

        TestSubscriber<List<Character>> result = new TestSubscriber<>();
        mDatabaseHelper.getCharacters().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(Collections.singletonList(characters));
    }
}