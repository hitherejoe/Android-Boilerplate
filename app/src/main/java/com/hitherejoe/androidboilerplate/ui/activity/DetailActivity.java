package com.hitherejoe.androidboilerplate.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.fragment.DetailFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {

    @Bind(R.id.sliding_tabs)
    TabLayout mTabLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.pager_character_detail)
    ViewPager mBeaconDetailViewPager;

    private static final String EXTRA_CHARACTER =
            "com.hitherejoe.androidboilerplate.ui.activity.DetailActivity.EXTRA_CHARACTER";
    private Character mCharacter;

    public static Intent getStartIntent(Context context, Character character) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_CHARACTER, character);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mCharacter = getIntent().getParcelableExtra(EXTRA_CHARACTER);
        if (mCharacter == null) {
            throw new IllegalArgumentException("DetailActivity requires a Character object!");
        }
        setupToolbar();
        setupViewPager();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mCharacter.name);
        }
    }

    private void setupViewPager() {
        mBeaconDetailViewPager.setOffscreenPageLimit(2);
        mBeaconDetailViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            String[] titles = getResources().getStringArray(R.array.detail_fragment_titles);

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return DetailFragment.newInstance((ArrayList<String>) mCharacter.films);
                    case 1:
                        return DetailFragment.newInstance((ArrayList<String>) mCharacter.vehicles);
                    case 2:
                        return DetailFragment.newInstance((ArrayList<String>) mCharacter.species);
                    case 3:
                        return DetailFragment.newInstance((ArrayList<String>) mCharacter.starships);
                    default:
                        return DetailFragment.newInstance(new ArrayList<String>());
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
        mTabLayout.setupWithViewPager(mBeaconDetailViewPager);
    }
}