package com.hitherejoe.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.hitherejoe.androidboilerplate.AndroidBoilerplateApplication;
import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.DataManager;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.adapter.CharacterHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

public class MainActivity extends BaseActivity {

    @Bind(R.id.recycler_characters)
    RecyclerView mCharactersRecycler;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.progress_indicator)
    ProgressBar mProgressBar;

    private DataManager mDataManager;
    private CompositeSubscription mSubscriptions;
    private EasyRecyclerAdapter<Character> mEasyRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSubscriptions = new CompositeSubscription();
        mDataManager = AndroidBoilerplateApplication.get(this).getComponent().dataManager();
        setupToolbar();
        setupRecyclerView();
        getAndroidBoilerPlates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_github:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void setupRecyclerView() {
        mCharactersRecycler.setLayoutManager(new LinearLayoutManager(this));
        mEasyRecycleAdapter = new EasyRecyclerAdapter<>(this, CharacterHolder.class);
        mCharactersRecycler.setAdapter(mEasyRecycleAdapter);
    }

    private void getAndroidBoilerPlates() {
        int[] avengerIds = getResources().getIntArray(R.array.avengers);
        List<Integer> list = new ArrayList<>(avengerIds.length);
        for (int value : avengerIds) {
            list.add(value);
        }
        mSubscriptions.add(mDataManager.getAvengers(list)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribe(new Subscriber<com.hitherejoe.androidboilerplate.data.model.Character>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBar.setVisibility(View.GONE);
                        Timber.e("Error getting characters");
                    }

                    @Override
                    public void onNext(Character character) {
                        mProgressBar.setVisibility(View.GONE);
                        mEasyRecycleAdapter.addItem(character);
                        mEasyRecycleAdapter.notifyDataSetChanged();
                    }
                }));
    }

}
