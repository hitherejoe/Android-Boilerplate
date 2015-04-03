package com.hitherejoe.androidboilerplate;


import com.hitherejoe.androidboilerplate.ui.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest extends BaseTestCase<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testActivityDisplayed() throws Exception {
        getActivity();
        onView(withText(R.string.hello_world)).check(matches(isDisplayed()));
    }
}