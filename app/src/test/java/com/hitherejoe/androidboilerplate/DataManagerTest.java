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
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = DefaultConfig.EMULATE_SDK)
public class DataManagerTest {

    private DataManager mDataManager;
    private AndroidBoilerplateService mBoilerplateService;

    @Before
    public void setUp() {
        mDataManager = new DataManager(RuntimeEnvironment.application, Schedulers.immediate());
        mBoilerplateService = mock(AndroidBoilerplateService.class);
        mDataManager.setAndroidBoilerplateService(mBoilerplateService);
    }

    @Test
    public void shouldGetBookmarks() throws Exception {
        Boilerplate mockBoilerplateOne = MockModelsUtil.createMockBoilerPlate();
        Boilerplate mockBoilerplateTwo = MockModelsUtil.createMockBoilerPlate();
        Boilerplate mockBoilerplateThree = MockModelsUtil.createMockBoilerPlate();
        List<Boilerplate> boilerplates = new ArrayList<>();
        boilerplates.add(mockBoilerplateOne);
        boilerplates.add(mockBoilerplateTwo);
        boilerplates.add(mockBoilerplateThree);
        when(mBoilerplateService.getAndroidBoilerplates()).thenReturn(Observable.just(boilerplates));

        final List<Boilerplate> boilerplateList = new ArrayList<>();
        mDataManager.getAndroidBoilerplates().subscribe(new Action1<Boilerplate>() {
            @Override
            public void call(Boilerplate story) {
                boilerplateList.add(story);
            }
        });
        Assert.assertEquals(3, boilerplateList.size());
        Assert.assertTrue(boilerplateList.contains(mockBoilerplateOne));
        Assert.assertTrue(boilerplateList.contains(mockBoilerplateTwo));
        Assert.assertTrue(boilerplateList.contains(mockBoilerplateThree));
    }

}
