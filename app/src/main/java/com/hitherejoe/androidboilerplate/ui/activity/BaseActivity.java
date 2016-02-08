package com.hitherejoe.androidboilerplate.ui.activity;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hitherejoe.androidboilerplate.AndroidBoilerplateApplication;
import com.hitherejoe.androidboilerplate.injection.component.ActivityComponent;
import com.hitherejoe.androidboilerplate.injection.component.ApplicationComponent;
import com.hitherejoe.androidboilerplate.injection.component.DaggerActivityComponent;
import com.hitherejoe.androidboilerplate.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(AndroidBoilerplateApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected ApplicationComponent applicationComponent() {
        return AndroidBoilerplateApplication.get(this).getComponent();
    }

}
