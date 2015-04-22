package uk.co.ribot.androidboilerplate.data.local;

import android.content.Context;
import android.database.Cursor;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class DatabaseHelper {

    private SqlBrite mDb;

    public DatabaseHelper(Context context) {
        mDb = SqlBrite.create(new DbOpenHelper(context));
    }

    public SqlBrite getDb() {
        return mDb;
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    public Observable<Void> clearTables() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                mDb.beginTransaction();
                try {
                    Cursor cursor = mDb.query("SELECT name FROM sqlite_master WHERE type='table'");
                    while (cursor.moveToNext()) {
                        mDb.delete(cursor.getString(cursor.getColumnIndex("name")), null);
                    }
                    cursor.close();
                    mDb.setTransactionSuccessful();
                    subscriber.onCompleted();
                } finally {
                    mDb.endTransaction();
                }
            }
        });
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

    public Observable<List<Ribot>> getRibots() {
        return mDb.createQuery(Db.RibotsTable.TABLE_NAME,
                "SELECT * FROM " + Db.RibotsTable.TABLE_NAME)
                .map(new Func1<SqlBrite.Query, List<Ribot>>() {
                    @Override
                    public List<Ribot> call(SqlBrite.Query query) {
                        Cursor cursor = query.run();
                        List<Ribot> result = new ArrayList<>();
                        while (cursor.moveToNext()) {
                            result.add(Db.RibotsTable.parseCursor(cursor));
                        }
                        cursor.close();
                        return result;
                    }
                });
    }

}
