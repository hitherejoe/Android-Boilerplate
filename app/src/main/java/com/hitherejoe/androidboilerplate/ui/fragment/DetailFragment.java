package com.hitherejoe.androidboilerplate.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.ui.adapter.DetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    @BindView(R.id.recycler_detail) RecyclerView mDetailRecycler;
    @BindView(R.id.text_no_data) TextView mNoDataText;

    private static final String ARG_ITEMS = "ARG_ITEMS";
    private List<String> mItems;

    public static DetailFragment newInstance(ArrayList<String> items) {
        DetailFragment propertiesFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_ITEMS, items);
        propertiesFragment.setArguments(args);
        return propertiesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = getArguments().getStringArrayList(ARG_ITEMS);
        if (mItems == null) {
            throw new IllegalArgumentException("DetailFragment requires a list of Item objects!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    private void setupRecyclerView() {
        mDetailRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (mItems != null && mItems.size() > 0) {
            DetailAdapter detailAdapter = new DetailAdapter();
            detailAdapter.setDetails(mItems);
            mDetailRecycler.setAdapter(detailAdapter);
        } else {
            mNoDataText.setVisibility(View.VISIBLE);
            mDetailRecycler.setVisibility(View.GONE);
        }
    }

}
