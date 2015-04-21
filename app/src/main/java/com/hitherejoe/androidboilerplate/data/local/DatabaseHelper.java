package com.hitherejoe.androidboilerplate.data.local;

import android.content.Context;
import android.database.Cursor;
import com.hitherejoe.androidboilerplate.data.model.Ribot;
import com.squareup.sqlbrite.SqlBrite;

import java.util.Collection;

import rx.Observable;
import rx.Subscriber;
import rx.android.content.ContentObservable;
import rx.functions.Func1;

public class DatabaseHelper {

    private SqlBrite mDb;

    public DatabaseHelper(Context context) {
        mDb = SqlBrite.create(new DbOpenHelper(context));
    }

    public SqlBrite getDb() {
        return mDb;
    }

    public Observable<Ribot> saveRibots(final Collection<Ribot> ribots) {
        return Observable.create(new Observable.OnSubscribe<Ribot>() {
            @Override
            public void call(Subscriber<? super Ribot> subscriber) {
                mDb.beginTransaction();
                try {
                    for (Ribot ribot : ribots) {
                        long result = mDb.insert(Db.RibotsTable.TABLE_NAME,
                                Db.RibotsTable.toContentValues(ribot));
                        if (result >= 0) subscriber.onNext(ribot);
                    }
                    mDb.setTransactionSuccessful();
                    subscriber.onCompleted();
                } finally {
                    mDb.endTransaction();
                }
            }
        });
    }

    public Observable<Ribot> getRibots() {
        return mDb.createQuery(Db.RibotsTable.TABLE_NAME,
                "SELECT * FROM " + Db.RibotsTable.TABLE_NAME)
                .flatMap(mRunQueryFunc)
                .map(Db.RibotsTable.PARSE_CURSOR_FUNC);
    }

    private Func1<SqlBrite.Query, Observable<Cursor>> mRunQueryFunc =
            new Func1<SqlBrite.Query, Observable<Cursor>>() {
                @Override
                public Observable<Cursor> call(SqlBrite.Query query) {
                    return ContentObservable.fromCursor(query.run());
                }
            };

}
