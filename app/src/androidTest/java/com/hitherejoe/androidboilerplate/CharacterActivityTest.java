package com.hitherejoe.androidboilerplate;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.activity.CharacterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CharacterActivityTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    public final ActivityTestRule<CharacterActivity> main =
            new ActivityTestRule<>(CharacterActivity.class, false, false);

    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void characterTextIsDisplayed() {
        Character mockCharacter = MockModelFabric.makeMockCharacter();
        Intent intent = CharacterActivity.getStartIntent(
                InstrumentationRegistry.getContext(), mockCharacter);
        main.launchActivity(intent);

        onView(withText(R.string.text_lorem_ipsum))
                .check(matches(isDisplayed()));
    }

}