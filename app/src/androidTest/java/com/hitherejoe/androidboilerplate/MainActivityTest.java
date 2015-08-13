package com.hitherejoe.androidboilerplate;


import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;

import com.hitherejoe.androidboilerplate.ui.activity.MainActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@MediumTest
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
    public void testActivityDisplayed() throws Exception {
        Intent i = new Intent(getTargetContext(), MainActivity.class);

        main.launchActivity(i);
        onView(withText(mContext.getString(R.string.hello_world))).check(matches(isDisplayed()));
    }
}