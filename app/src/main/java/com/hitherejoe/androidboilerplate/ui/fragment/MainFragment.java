package com.hitherejoe.androidboilerplate.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hitherejoe.androidboilerplate.R;


import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainFragment extends Fragment {

    @InjectView(R.id.text_hello_world)
    TextView mHelloWorldText;

    private static final String TAG = "StoriesFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
