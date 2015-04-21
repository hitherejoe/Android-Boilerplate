package com.hitherejoe.androidboilerplate;


import com.hitherejoe.androidboilerplate.data.DataManager;
import com.hitherejoe.androidboilerplate.data.model.Boilerplate;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.util.DefaultConfig;
import com.hitherejoe.androidboilerplate.util.MockModelsUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = DefaultConfig.EMULATE_SDK)
public class DataManagerTest {

    private DataManager mDataManager;
    private AndroidBoilerplateService mBoilerplateService;

    @Before
    public void setUp() {
        mDataManager = new DataManager(Robolectric.application, Schedulers.immediate());
        mBoilerplateService = mock(AndroidBoilerplateService.class);
        mDataManager.setAndroidBoilerplateService(mBoilerplateService);
    }

}
