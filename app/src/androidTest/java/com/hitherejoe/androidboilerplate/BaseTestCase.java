package com.hitherejoe.androidboilerplate;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;

import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

public class BaseTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    protected AndroidBoilerplateService mAndroidBoilerplateService;

    public BaseTestCase(Class<T> cls) {
        super(cls);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        AndroidBoilerplateApplication.get().getDataManager().getPreferencesHelper().clear();
        AndroidBoilerplateApplication.get().getDataManager().getDatabaseHelper().clearBoilerplates().subscribe();
        mAndroidBoilerplateService = mock(AndroidBoilerplateService.class);
        AndroidBoilerplateApplication.get().getDataManager().setAndroidBoilerplateService(mAndroidBoilerplateService);
        AndroidBoilerplateApplication.get().getDataManager().setScheduler(Schedulers.immediate());
    }

}