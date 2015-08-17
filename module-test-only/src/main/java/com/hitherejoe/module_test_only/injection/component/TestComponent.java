package com.hitherejoe.module_test_only.injection.component;

import com.hitherejoe.androidboilerplate.injection.component.ApplicationComponent;
import com.hitherejoe.module_test_only.injection.module.ApplicationTestModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}