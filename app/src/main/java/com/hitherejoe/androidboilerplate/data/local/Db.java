package com.hitherejoe.androidboilerplate.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.hitherejoe.androidboilerplate.data.model.Boilerplate;
import com.hitherejoe.androidboilerplate.data.model.Ribot;

import rx.functions.Func1;

public class Db {

    public Db() { }

    public static abstract class RibotsTable {
        public static final String TABLE_NAME = "ribots";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_HEX_CODE = "hex_code";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_ROLE = "role";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " TEXT PRIMARY KEY ON CONFLICT REPLACE, " +
                    COLUMN_HEX_CODE + " TEXT NOT NULL, " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_LAST_NAME + " TEXT, " +
                    COLUMN_ROLE + " TEXT" +
                " ); ";

        public static ContentValues toContentValues(Ribot ribot) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, ribot.id);
            values.put(COLUMN_HEX_CODE, ribot.hexCode);
            values.put(COLUMN_FIRST_NAME, ribot.info.firstName);
            values.put(COLUMN_LAST_NAME, ribot.info.lastName);
            values.put(COLUMN_ROLE, ribot.info.role);
            return values;
        }

        public static Ribot parseCursor(Cursor cursor) {
            Ribot ribot = new Ribot();
            ribot.id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
            ribot.hexCode = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEX_CODE));
            ribot.info = new Ribot.Info();
            ribot.info.firstName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
            ribot.info.lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME));
            ribot.info.role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE));
            return ribot;
        }

    }
}
