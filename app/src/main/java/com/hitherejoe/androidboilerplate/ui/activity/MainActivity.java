package com.hitherejoe.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hitherejoe.androidboilerplate.AndroidBoilerplateApplication;
import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.DataManager;
import com.hitherejoe.androidboilerplate.data.model.Boilerplate;
import com.hitherejoe.androidboilerplate.ui.adapter.BoilerplateHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.app.AppObservable;
import rx.subscriptions.CompositeSubscription;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.text_hello_world)
    TextView mHelloWorldText;

    private static final String TAG = "MainActivity";
    private DataManager mDataManager;
    private CompositeSubscription mSubscriptions;
    private EasyRecyclerAdapter<Boilerplate> mEasyRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mSubscriptions = new CompositeSubscription();
        mDataManager = AndroidBoilerplateApplication.get().getDataManager();
        mEasyRecycleAdapter = new EasyRecyclerAdapter<>(this, BoilerplateHolder.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getAndroidBoilerPlates() {
            mSubscriptions.add(AppObservable.bindFragment(this,
                    mDataManager.getAndroidBoilerplates())
                    .subscribeOn(mDataManager.getScheduler())
                    .subscribe(new Subscriber<Boilerplate>() {
                        @Override
                        public void onCompleted() { }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "There was a problem getting the boilder plates " + e);
                        }

                        @Override
                        public void onNext(Boilerplate boilerplate) {
                            mEasyRecycleAdapter.addItem(boilerplate);
                        }
                    }));
    }

}
