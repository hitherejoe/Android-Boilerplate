package uk.co.ribot.androidboilerplate.injection.component;

import dagger.Component;
import uk.co.ribot.androidboilerplate.injection.module.DataManagerTestModule;
import uk.co.ribot.androidboilerplate.injection.scope.PerDataManager;

@PerDataManager
@Component(dependencies = ApplicationTestComponent.class, modules = DataManagerTestModule.class)
public interface DataManagerTestComponent extends DataManagerComponent {
}
