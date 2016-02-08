package com.hitherejoe.androidboilerplate;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import timber.log.Timber;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<>(MainActivity.class, false, false);

    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void loadCharactersShowsCharactersFeed() {
        Context context = InstrumentationRegistry.getTargetContext();
        int[] characterIds =
                context.getResources().getIntArray(R.array.characters);
        List<Character> mockCharacters =
                MockModelFabric.makeListOfMockCharacters(characterIds.length);
        stubMockCharacters(Observable.just(mockCharacters));
        main.launchActivity(null);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException error) {
            Timber.e(error, "Interrupted exception occurred");
        }
        checkCharactersDisplayOnRecyclerView(mockCharacters);
    }

    @Test
    public void loadCharactersErrorShowsEmptyMessage() {
        doReturn(Observable.<List<Character>>empty())
                .when(component.getMockDataManager())
                .getCharacters(any(int[].class));
        Character mockCharacter = MockModelFabric.makeMockCharacter();
        List<Character> mockCharacters = new ArrayList<>();
        mockCharacters.add(mockCharacter);
        stubMockCharacters(Observable.just(mockCharacters));
        main.launchActivity(null);
        checkCharactersDisplayOnRecyclerView(mockCharacters);
    }

    @Test
    public void characterScreenOpensWhenClickingOnCard() {
        Context context = InstrumentationRegistry.getTargetContext();
        int[] characterIds =
                context.getResources().getIntArray(R.array.characters);
        List<Character> mockCharacters =
                MockModelFabric.makeListOfMockCharacters(characterIds.length);
        stubMockCharacters(Observable.just(mockCharacters));
        main.launchActivity(null);
        onView(withText(mockCharacters.get(0).name))
                .perform(click());
        onView(withText(com.hitherejoe.androidboilerplate.R.string.text_lorem_ipsum))
                .check(matches(isDisplayed()));
    }

    @Test
    public void characterDetailsScreenOpens() {
        doReturn(Observable.<List<Character>>empty())
                .when(component.getMockDataManager())
                .getCharacters(any(int[].class));
        Character mockCharacter = MockModelFabric.makeMockCharacter();
        List<Character> mockCharacters = new ArrayList<>();
        mockCharacters.add(mockCharacter);
        stubMockCharacters(Observable.just(mockCharacters));
        main.launchActivity(null);
        onView(withText("View"))
                .perform(click());
        onView(withText(com.hitherejoe.androidboilerplate.R.string.text_lorem_ipsum))
                .check(matches(isDisplayed()));
    }

    @Test
    public void collectionsScreenOpens() {
        doReturn(Observable.<List<Character>>empty())
                .when(component.getMockDataManager())
                .getCharacters(any(int[].class));
        Character mockCharacter = MockModelFabric.makeMockCharacter();
        List<Character> mockCharacters = new ArrayList<>();
        mockCharacters.add(mockCharacter);
        stubMockCharacters(Observable.just(mockCharacters));
        main.launchActivity(null);
        onView(withText("Collections"))
                .perform(click());
        onView(withText("Films"))
                .check(matches(isDisplayed()));
    }

    private void checkCharactersDisplayOnRecyclerView(List<Character> charactersToCheck) {
        for (int i = 0; i < charactersToCheck.size(); i++) {
            onView(withId(R.id.recycler_characters))
                    .perform(RecyclerViewActions.scrollToPosition(i));
            checkCharacterDisplays(charactersToCheck.get(i));
        }
    }

    private void checkCharacterDisplays(Character character) {
        onView(withText(character.name))
                .check(matches(isDisplayed()));
    }

    private void stubMockCharacters(Observable<List<Character>> observable) {
        when(component.getMockDataManager().getCharacters(any(int[].class)))
                .thenReturn(observable);
    }
}