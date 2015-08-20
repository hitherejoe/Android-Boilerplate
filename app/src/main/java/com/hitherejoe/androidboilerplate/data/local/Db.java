package com.hitherejoe.androidboilerplate.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.hitherejoe.androidboilerplate.data.model.Character;

public class Db {

    public Db() { }

    public static abstract class BoilerplateTable {
        public static final String TABLE_NAME = "boilerplate";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_BOILERPLATE = "android_boilerplate";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_BOILERPLATE + " TEXT NOT NULL" +
                        " ); ";

        public static ContentValues toContentValues(Character boilerplate) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, boilerplate.id);
           // values.put(COLUMN_BOILERPLATE, boilerplate.androidBoilerplate);
            return values;
        }

        public static Character parseCursor(Cursor cursor) {
            Character boilerplate = new Character();
            boilerplate.id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            //boilerplate.androidBoilerplate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BOILERPLATE));
            return boilerplate;
        }
    }
}
