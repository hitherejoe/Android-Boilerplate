package uk.co.ribot.androidboilerplate;

import android.support.test.espresso.contrib.RecyclerViewActions;

import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.activity.MainActivity;
import uk.co.ribot.androidboilerplate.util.MockModelsUtil;

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

    public void testListOfRibotsShows() throws Exception {
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