package com.hitherejoe.androidboilerplate.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.data.model.Collection;
import com.hitherejoe.androidboilerplate.util.DataUtils;

public class Db {

    public Db() { }

    public static abstract class CharacterTable {
        public static final String TABLE_NAME = "characters";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_THUMBNAIL = "thumbnail";
        public static final String COLUMN_COMICS = "comics";
        public static final String COLUMN_SERIES = "series";
        public static final String COLUMN_STORIES = "stories";
        public static final String COLUMN_EVENTS = "events";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME + " TEXT NOT NULL," +
                        COLUMN_DESCRIPTION + " TEXT," +
                        COLUMN_THUMBNAIL + " TEXT," +
                        COLUMN_COMICS + " TEXT," +
                        COLUMN_SERIES + " TEXT," +
                        COLUMN_STORIES + " TEXT," +
                        COLUMN_EVENTS + " TEXT" +
                        " ); ";

        public static ContentValues toContentValues(Character character) {
            Gson gson = DataUtils.getGsonInstance();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, character.id);
            values.put(COLUMN_NAME, character.name);
            values.put(COLUMN_DESCRIPTION, character.description);
            values.put(COLUMN_THUMBNAIL, gson.toJson(character.thumbnail, new TypeToken<Character.Thumbnail>(){}.getType()));
            values.put(COLUMN_COMICS, gson.toJson(character.comics, new TypeToken<Collection>(){}.getType()));
            values.put(COLUMN_SERIES, gson.toJson(character.series, new TypeToken<Collection>(){}.getType()));
            values.put(COLUMN_STORIES, gson.toJson(character.stories, new TypeToken<Collection>(){}.getType()));
            values.put(COLUMN_EVENTS, gson.toJson(character.events, new TypeToken<Collection>(){}.getType()));
            return values;
        }

        public static Character parseCursor(Cursor cursor) {
            Gson gson = DataUtils.getGsonInstance();
            Character character = new Character();
            character.id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            character.name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            character.description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
            character.thumbnail = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_THUMBNAIL)), new TypeToken<Character.Thumbnail>() {}.getType());
            character.comics = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMICS)), new TypeToken<Collection>() {}.getType());
            character.series = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERIES)), new TypeToken<Collection>() {}.getType());
            character.stories = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STORIES)), new TypeToken<Collection>() {}.getType());
            character.events = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EVENTS)), new TypeToken<Collection>() {}.getType());
            return character;
        }
    }
}
