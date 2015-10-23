package com.hitherejoe.androidboilerplate.data.local;

import android.content.Context;
import android.database.Cursor;

import com.hitherejoe.androidboilerplate.data.model.Character;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.BriteDatabase.Transaction;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class DatabaseHelper {

    private BriteDatabase mBriteDb;

    public DatabaseHelper(Context context) {
        mBriteDb = SqlBrite.create().wrapDatabaseHelper(new DbOpenHelper(context));
    }

    public BriteDatabase getBriteDb() {
        return mBriteDb;
    }

    public Observable<Character> saveCharacter(final Character story) {
        return Observable.create(new Observable.OnSubscribe<Character>() {
            @Override
            public void call(Subscriber<? super Character> subscriber) {
                mBriteDb.insert(Db.CharacterTable.TABLE_NAME, Db.CharacterTable.toContentValues(story));
                subscriber.onNext(story);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<Character> setCharacters(final List<Character> characters) {
        return Observable.create(new Observable.OnSubscribe<Character>() {
            @Override
            public void call(Subscriber<? super Character> subscriber) {
                Transaction transaction = mBriteDb.newTransaction();
                try {
                    mBriteDb.delete(Db.CharacterTable.TABLE_NAME, null);
                    for (Character character : characters) {
                        mBriteDb.insert(Db.CharacterTable.TABLE_NAME, Db.CharacterTable.toContentValues(character));
                        subscriber.onNext(character);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Character>> getCharacters() {
        return mBriteDb.createQuery(Db.CharacterTable.TABLE_NAME,
                "SELECT * FROM " + Db.CharacterTable.TABLE_NAME)
                .map(new Func1<SqlBrite.Query, List<Character>>() {
                    @Override
                    public List<Character> call(SqlBrite.Query query) {
                        Cursor cursor = query.run();
                        List<Character> result = new ArrayList<>();
                        while (cursor.moveToNext()) {
                            result.add(Db.CharacterTable.parseCursor(cursor));
                        }
                        cursor.close();
                        return result;
                    }
                });
    }

    public Observable<Void> deleteCharacters() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                Transaction transaction = mBriteDb.newTransaction();
                try {
                    mBriteDb.delete(Db.CharacterTable.TABLE_NAME, null);
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<Void> clearTables() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                Transaction transaction = mBriteDb.newTransaction();
                try {
                    Cursor cursor = mBriteDb.query("SELECT name FROM sqlite_master WHERE type='table'");
                    while (cursor.moveToNext()) {
                        mBriteDb.delete(cursor.getString(cursor.getColumnIndex("name")), null);
                    }
                    cursor.close();
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

}
