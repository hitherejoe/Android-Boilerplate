package uk.co.ribot.androidboilerplate.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.injection.component.ApplicationComponent;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent applicationComponent() {
        return BoilerplateApplication.get(this).getComponent();
    }

}
