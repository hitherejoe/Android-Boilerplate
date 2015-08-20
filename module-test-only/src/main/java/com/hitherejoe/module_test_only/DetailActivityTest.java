package com.hitherejoe.module_test_only;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.activity.DetailActivity;
import com.hitherejoe.androidboilerplate.util.MockModelsUtil;
import com.hitherejoe.module_test_only.injection.TestComponentRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private Context mContext;

    @Rule
    public final ActivityTestRule<DetailActivity> main =
            new ActivityTestRule<>(DetailActivity.class, false, false);

    @Rule
    public final TestComponentRule component = new TestComponentRule();

    @Before
    public void initTargetContext() {
        mContext = getTargetContext();
    }

    @Test
    public void testCharacterCollectionsDisplayed() {
        Character mockCharacter = MockModelsUtil.createMockCharacter();
        Intent i = DetailActivity.getStartIntent(mContext, mockCharacter);
        main.launchActivity(i);
        String[] tabTitles =
                InstrumentationRegistry.getTargetContext().getResources().getStringArray(com.hitherejoe.androidboilerplate.R.array.detail_fragment_titles);
        checkTextIsShownInTab(tabTitles[0], mockCharacter.films);
        checkTextIsShownInTab(tabTitles[1], mockCharacter.vehicles);
        checkTextIsShownInTab(tabTitles[2], mockCharacter.species);
        checkTextIsShownInTab(tabTitles[3], mockCharacter.starships);
    }

    private void checkTextIsShownInTab(String tab, List<String> items) {
        onView(withText(tab)).perform(click());
        if (items.isEmpty()) {
            onView(withText(R.string.text_no_data)).check(matches(isDisplayed()));
        } else {
            for (String item : items) onView(withText(item)).check(matches(isDisplayed()));
        }
    }

}