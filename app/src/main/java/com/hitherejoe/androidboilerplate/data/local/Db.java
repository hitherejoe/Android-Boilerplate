package com.hitherejoe.androidboilerplate.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.util.DataUtils;

import java.util.List;

public class Db {

    public Db() { }

    public static abstract class CharacterTable {
        public static final String TABLE_NAME = "characters";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_MASS = "mass";
        public static final String COLUMN_HAIR_COLOR = "hair_color";
        public static final String COLUMN_SKIN_COLOR = "skin_color";
        public static final String COLUMN_EYE_COLOR = "eye_color";
        public static final String COLUMN_BIRTH_YEAR = "birth_year";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_HOME_WORLD = "home_world";
        public static final String COLUMN_FILMS = "films";
        public static final String COLUMN_SPECIES = "species";
        public static final String COLUMN_VEHICLES = "vehicles";
        public static final String COLUMN_STARSHIPS = "starships";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        COLUMN_NAME + " TEXT NOT NULL," +
                        COLUMN_HEIGHT + " TEXT," +
                        COLUMN_MASS + " TEXT," +
                        COLUMN_HAIR_COLOR + " TEXT," +
                        COLUMN_SKIN_COLOR + " TEXT," +
                        COLUMN_EYE_COLOR + " TEXT," +
                        COLUMN_BIRTH_YEAR + " TEXT," +
                        COLUMN_GENDER + " TEXT," +
                        COLUMN_HOME_WORLD + " TEXT," +
                        COLUMN_FILMS + " TEXT," +
                        COLUMN_SPECIES + " TEXT," +
                        COLUMN_VEHICLES + " TEXT," +
                        COLUMN_STARSHIPS + " TEXT" +
                        " ); ";

        public static ContentValues toContentValues(Character character) {
            Gson gson = DataUtils.getGsonInstance();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, character.name);
            values.put(COLUMN_HEIGHT, character.height);
            values.put(COLUMN_MASS, character.mass);
            values.put(COLUMN_HAIR_COLOR, character.hairColor);
            values.put(COLUMN_SKIN_COLOR, character.skinColor);
            values.put(COLUMN_EYE_COLOR, character.eyeColor);
            values.put(COLUMN_BIRTH_YEAR, character.birthYear);
            values.put(COLUMN_GENDER, character.gender);
            values.put(COLUMN_HOME_WORLD, character.homeworld);
            values.put(COLUMN_FILMS, gson.toJson(character.films, new TypeToken<List<String>>(){}.getType()));
            values.put(COLUMN_SPECIES, gson.toJson(character.species, new TypeToken<List<String>>(){}.getType()));
            values.put(COLUMN_VEHICLES, gson.toJson(character.vehicles, new TypeToken<List<String>>(){}.getType()));
            values.put(COLUMN_STARSHIPS, gson.toJson(character.starships, new TypeToken<List<String>>(){}.getType()));
            return values;
        }

        public static Character parseCursor(Cursor cursor) {
            Gson gson = DataUtils.getGsonInstance();
            Character character = new Character();
            character.name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            character.height = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEIGHT));
            character.mass = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MASS));
            character.hairColor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SKIN_COLOR));
            character.eyeColor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EYE_COLOR));
            character.birthYear = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BIRTH_YEAR));
            character.gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER));
            character.homeworld = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOME_WORLD));
            character.films = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FILMS)), new TypeToken<List<String>>() {}.getType());
            character.species = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPECIES)), new TypeToken<List<String>>() {}.getType());
            character.vehicles = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLES)), new TypeToken<List<String>>() {}.getType());
            character.starships = gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STARSHIPS)), new TypeToken<List<String>>() {}.getType());
            return character;
        }
    }
}
