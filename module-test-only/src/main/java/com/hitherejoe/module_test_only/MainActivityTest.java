package com.hitherejoe.module_test_only;


import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hitherejoe.androidboilerplate.ui.activity.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private Context mContext;

    @Rule
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void initTargetContext() {
        mContext = getTargetContext();
    }

    @Test
    public void testActivityDisplayed() {
        Intent i = new Intent(getTargetContext(), MainActivity.class);

        main.launchActivity(i);
        onView(withText(mContext.getString(com.hitherejoe.androidboilerplate.R.string.app_name))).check(matches(isDisplayed()));
    }
}