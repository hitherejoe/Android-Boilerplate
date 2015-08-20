package com.hitherejoe.androidboilerplate.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hitherejoe.androidboilerplate.data.model.Character;

import rx.Observable;
import rx.Subscriber;

public class DatabaseHelper {

    private DbOpenHelper mDatabaseOpenHelper;

    public DatabaseHelper(Context context) {
        mDatabaseOpenHelper = new DbOpenHelper(context);
    }

    public SQLiteDatabase getReadableDatabase() {
        return mDatabaseOpenHelper.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return mDatabaseOpenHelper.getWritableDatabase();
    }

    public Observable<Character> saveBoilerplate(final Character story) {
        return Observable.create(new Observable.OnSubscribe<Character>() {
            @Override
            public void call(Subscriber<? super Character> subscriber) {
                SQLiteDatabase db = getWritableDatabase();
                db.insertOrThrow(Db.BoilerplateTable.TABLE_NAME, null, Db.BoilerplateTable.toContentValues(story));
                subscriber.onNext(story);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<Void> clearBoilerplates() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                SQLiteDatabase db = getWritableDatabase();
                db.beginTransaction();
                try {
                    Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
                    while (cursor.moveToNext()) {
                        db.delete(cursor.getString(cursor.getColumnIndex("name")), null, null);
                    }
                    cursor.close();
                    db.setTransactionSuccessful();
                    subscriber.onCompleted();
                } finally {
                    db.endTransaction();
                }
            }
        });
    }
}
