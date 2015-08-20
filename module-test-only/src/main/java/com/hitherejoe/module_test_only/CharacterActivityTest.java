package com.hitherejoe.module_test_only;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.activity.CharacterActivity;
import com.hitherejoe.androidboilerplate.util.MockModelsUtil;
import com.hitherejoe.module_test_only.injection.TestComponentRule;

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
public class CharacterActivityTest {

    private Context mContext;

    @Rule
    public final ActivityTestRule<CharacterActivity> main =
            new ActivityTestRule<>(CharacterActivity.class, false, false);

    @Rule
    public final TestComponentRule component = new TestComponentRule();

    @Before
    public void initTargetContext() {
        mContext = getTargetContext();
    }

    @Test
    public void testCharacterTextIsDisplayed() {
        Character mockCharacter = MockModelsUtil.createMockCharacter();
        Intent i = CharacterActivity.getStartIntent(mContext, mockCharacter);
        main.launchActivity(i);

        onView(withText(com.hitherejoe.androidboilerplate.R.string.text_lorem_ipsum))
                .check(matches(isDisplayed()));
    }

}