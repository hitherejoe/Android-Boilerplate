package com.hitherejoe.androidboilerplate.injection.module;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;

import com.hitherejoe.androidboilerplate.data.DataManager;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
public class ApplicationTestModule {
    protected final Application mApplication;

    public ApplicationTestModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    /************* MOCKS *************/

    @Provides
    @Singleton
    DataManager providesDataManager() {
        return mock(DataManager.class);
    }

    @Provides
    @Singleton
    AndroidBoilerplateService provideRibotService() {
        return mock(AndroidBoilerplateService.class);
    }

    @Provides
    @Singleton
    AccountManager provideAccountManager() {
        return mock(AccountManager.class);
    }
}
