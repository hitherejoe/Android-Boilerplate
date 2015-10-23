package com.hitherejoe.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.hitherejoe.androidboilerplate.data.SyncService;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.adapter.CharacterHolder;
import com.hitherejoe.androidboilerplate.util.DataUtils;
import com.hitherejoe.androidboilerplate.util.DialogFactory;

import java.util.ArrayList;

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

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefresh;

    private DataManager mDataManager;
    private CompositeSubscription mSubscriptions;
    private EasyRecyclerAdapter<Character> mEasyRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationComponent().inject(this);
        startService(SyncService.getStartIntent(this));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSubscriptions = new CompositeSubscription();
        mDataManager = AndroidBoilerplateApplication.get(this).getComponent().dataManager();
        setupToolbar();
        setupRecyclerView();
        loadCharacters();
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

        mSwipeRefresh.setColorSchemeResources(R.color.primary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mEasyRecycleAdapter.setItems(new ArrayList<Character>());
                reSyncCharacters();
            }
        });
    }

    private void reSyncCharacters() {
        if (DataUtils.isNetworkAvailable(this)) {
            int[] characterIds = getResources().getIntArray(R.array.characters);
            mSubscriptions.add(mDataManager.syncCharacters(characterIds)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(mDataManager.getSubscribeScheduler())
                    .subscribe(new Subscriber<Character>() {
                        @Override
                        public void onCompleted() { }

                        @Override
                        public void onError(Throwable error) {
                            Timber.e("There was an error retrieving the characters " + error);
                            mProgressBar.setVisibility(View.GONE);
                            mSwipeRefresh.setRefreshing(false);
                            DialogFactory.createSimpleErrorDialog(MainActivity.this).show();
                        }

                        @Override
                        public void onNext(Character character) {
                            mProgressBar.setVisibility(View.GONE);
                            mSwipeRefresh.setRefreshing(false);
                            mEasyRecycleAdapter.addItem(character);
                            mEasyRecycleAdapter.notifyDataSetChanged();
                        }
                    }));
        } else {
            mProgressBar.setVisibility(View.GONE);
            mSwipeRefresh.setRefreshing(false);
            DialogFactory.createSimpleOkErrorDialog(
                    this,
                    getString(R.string.dialog_error_title),
                    getString(R.string.dialog_error_no_connection)
            ).show();
        }
    }

    private void loadCharacters() {
        mSubscriptions.add(mDataManager.loadCharacters()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getSubscribeScheduler())
                .subscribe(new Subscriber<Character>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable error) {
                        Timber.e("There was an error retrieving the characters " + error);
                        mProgressBar.setVisibility(View.GONE);
                        mSwipeRefresh.setRefreshing(false);
                        DialogFactory.createSimpleErrorDialog(MainActivity.this).show();
                    }

                    @Override
                    public void onNext(Character character) {
                        mProgressBar.setVisibility(View.GONE);
                        mSwipeRefresh.setRefreshing(false);
                        mEasyRecycleAdapter.addItem(character);
                        mEasyRecycleAdapter.notifyDataSetChanged();
                    }
                }));
    }

}
