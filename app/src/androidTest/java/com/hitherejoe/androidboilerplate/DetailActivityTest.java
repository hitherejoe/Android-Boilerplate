package com.hitherejoe.androidboilerplate;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.activity.DetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    public final ActivityTestRule<DetailActivity> main =
            new ActivityTestRule<>(DetailActivity.class, false, false);

    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void characterCollectionsDisplayed() {
        Character mockCharacter = MockModelFabric.makeMockCharacter();
        Intent intent = DetailActivity.getStartIntent(
                InstrumentationRegistry.getContext(), mockCharacter);
        main.launchActivity(intent);
        Context context = InstrumentationRegistry.getTargetContext();
        String[] tabTitles =
                context.getResources().getStringArray(R.array.detail_fragment_titles);
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
            for (String item : items) {
                onView(withText(item)).check(matches(isDisplayed()));
            }
        }
    }

}