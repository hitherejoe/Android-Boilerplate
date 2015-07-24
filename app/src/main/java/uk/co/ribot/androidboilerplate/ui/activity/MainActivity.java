package uk.co.ribot.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import uk.co.ribot.androidboilerplate.AndroidBoilerplateApplication;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.SyncService;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.adapter.RibotItemViewHolder;

import java.util.List;

import butterknife.ButterKnife;
import rx.android.app.AppObservable;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private DataManager mDataManager;
    private CompositeSubscription mSubscriptions;
    private EasyRecyclerAdapter<Ribot> mRecyclerAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(SyncService.getStartIntent(this));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSubscriptions = new CompositeSubscription();
        mDataManager = AndroidBoilerplateApplication.get().getDataManager();
        mRecyclerAdapter = new EasyRecyclerAdapter<>(this, RibotItemViewHolder.class);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadRibots();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    private void loadRibots() {
        mSubscriptions.add(AppObservable.bindActivity(this, mDataManager.getRibots())
                .subscribeOn(mDataManager.getScheduler())
                .subscribe(new Action1<List<Ribot>>() {
                    @Override
                    public void call(List<Ribot> ribots) {
                        mRecyclerAdapter.setItems(ribots);
                    }
                }));
    }

}
