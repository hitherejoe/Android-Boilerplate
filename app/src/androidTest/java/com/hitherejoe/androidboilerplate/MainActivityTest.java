package com.hitherejoe.androidboilerplate;


import android.support.test.espresso.contrib.RecyclerViewActions;

import com.hitherejoe.androidboilerplate.data.model.Ribot;
import com.hitherejoe.androidboilerplate.ui.activity.MainActivity;
import com.hitherejoe.androidboilerplate.util.MockModelsUtil;

import org.junit.Test;

import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

public class MainActivityTest extends BaseTestCase<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Test
    public void checkListOfRibotsShows() throws Exception {
        List<Ribot> mockRibots = MockModelsUtil.createListRibots(20);
        when(mMockRibotsService.getRibots())
                .thenReturn(Observable.just(mockRibots));

        getActivity();

        onView(withText(R.string.app_name))
                .check(matches(isDisplayed()));

        int position = 0;
        for (Ribot mockRibot : mockRibots) {
            onView(withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.scrollToPosition(position));
            onView(withText(mockRibot.info.firstName + " " + mockRibot.info.lastName))
                    .check(matches(isDisplayed()));
            onView(withText(mockRibot.info.role))
                    .check(matches(isDisplayed()));
            position++;
        }

    }
}