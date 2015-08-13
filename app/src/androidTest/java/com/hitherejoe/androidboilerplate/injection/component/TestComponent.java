package com.hitherejoe.androidboilerplate.injection.component;

import com.hitherejoe.androidboilerplate.injection.module.ApplicationTestModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}