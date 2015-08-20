package com.hitherejoe.androidboilerplate;

import android.database.Cursor;

import com.hitherejoe.androidboilerplate.data.local.DatabaseHelper;
import com.hitherejoe.androidboilerplate.data.local.Db;
import com.hitherejoe.androidboilerplate.util.DefaultConfig;
import com.hitherejoe.androidboilerplate.util.MockModelsUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import rx.functions.Action1;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

    private DatabaseHelper mDatabaseHelper;
    private Boilerplate mBoilerplate;

    @Before
    public void setUp() {
        mDatabaseHelper = new DatabaseHelper(RuntimeEnvironment.application);
    }

    @Test
    public void shouldAddBoilerplate() throws Exception {
        Boilerplate mockBoilerplate = MockModelsUtil.createMockBoilerPlate();

        mDatabaseHelper.saveBoilerplate(mockBoilerplate).subscribe(new Action1<Boilerplate>() {
            @Override
            public void call(Boilerplate boilerplate) {
                mBoilerplate = boilerplate;
            }
        });
        Assert.assertEquals(mockBoilerplate, mBoilerplate);

        Cursor cursor = mDatabaseHelper.getReadableDatabase().query(Db.BoilerplateTable.TABLE_NAME,
                null,
                Db.BoilerplateTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(mockBoilerplate.id)},
                null, null, null);
        cursor.moveToFirst();
        Boilerplate boilerplateResult = Db.BoilerplateTable.parseCursor(cursor);
        Assert.assertEquals(mockBoilerplate, boilerplateResult);
        cursor.close();
    }

}