package uk.co.ribot.androidboilerplate.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import uk.co.ribot.androidboilerplate.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface ApplicationTestComponent extends ApplicationComponent {

}
