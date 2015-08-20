package com.hitherejoe.androidboilerplate;


import android.database.Cursor;

import com.hitherejoe.androidboilerplate.data.DataManager;
import com.hitherejoe.androidboilerplate.data.local.DatabaseHelper;
import com.hitherejoe.androidboilerplate.data.local.Db;
import com.hitherejoe.androidboilerplate.data.local.PreferencesHelper;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.util.DefaultConfig;
import com.hitherejoe.androidboilerplate.util.MockModelsUtil;
import com.squareup.otto.Bus;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

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
    private AndroidBoilerplateService mMockAndroidBoilerplateService;
    private DatabaseHelper mDatabaseHelper;

    @Before
    public void setUp() {
        mMockAndroidBoilerplateService = mock(AndroidBoilerplateService.class);
        mDatabaseHelper = new DatabaseHelper(RuntimeEnvironment.application);
        mDataManager = new DataManager(mMockAndroidBoilerplateService,
                mDatabaseHelper,
                mock(Bus.class),
                new PreferencesHelper(RuntimeEnvironment.application),
                Schedulers.immediate());
    }

    @Test
    public void shouldSyncCharacters() throws Exception {
        int[] ids = RuntimeEnvironment.application.getResources().getIntArray(R.array.avengers);
        List<Character> characters = MockModelsUtil.createListOfMockCharacters(ids);
        for (Character character : characters) {
            when(mMockAndroidBoilerplateService.getCharacter(character.id))
                    .thenReturn(Observable.just(MockModelsUtil.createMockCharacterResponse(character)));
        }

        TestSubscriber<Character> result = new TestSubscriber<>();
        mDataManager.syncCharacters(ids).subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(characters);

        Cursor cursor = mDatabaseHelper.getBriteDb()
                .query("SELECT * FROM " + Db.CharacterTable.TABLE_NAME);
        assertEquals(20, cursor.getCount());
        cursor.close();
    }

}
