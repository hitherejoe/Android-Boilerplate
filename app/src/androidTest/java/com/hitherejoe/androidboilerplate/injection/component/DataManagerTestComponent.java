package com.hitherejoe.androidboilerplate.injection.component;

import com.hitherejoe.androidboilerplate.injection.scope.PerDataManager;
import com.hitherejoe.androidboilerplate.injection.module.DataManagerTestModule;

import dagger.Component;

@PerDataManager
@Component(dependencies = TestComponent.class, modules = DataManagerTestModule.class)
public interface DataManagerTestComponent extends DataManagerComponent {
}
