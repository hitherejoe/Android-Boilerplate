package com.hitherejoe.module_test_only.injection.component;

import com.hitherejoe.androidboilerplate.injection.component.DataManagerComponent;
import com.hitherejoe.androidboilerplate.injection.scope.PerDataManager;
import com.hitherejoe.module_test_only.injection.module.DataManagerTestModule;

import dagger.Component;

@PerDataManager
@Component(dependencies = TestComponent.class, modules = DataManagerTestModule.class)
public interface DataManagerTestComponent extends DataManagerComponent {
}
