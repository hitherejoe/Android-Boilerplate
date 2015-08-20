package com.hitherejoe.module_test_only;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.ui.activity.MainActivity;
import com.hitherejoe.androidboilerplate.util.MockModelsUtil;
import com.hitherejoe.module_test_only.injection.TestComponentRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<>(MainActivity.class, false, false);

    @Rule
    public final TestComponentRule component = new TestComponentRule();

    @Test
    public void testCharactersShowAndAreScrollableInFeed() {
        int[] characterIds =
                InstrumentationRegistry.getTargetContext().getResources().getIntArray(com.hitherejoe.androidboilerplate.R.array.avengers);
        List<Character> mockCharacters = MockModelsUtil.createListOfMockCharacters(characterIds);
        stubMockPosts(characterIds, mockCharacters);
        main.launchActivity(null);
        checkPostsDisplayOnRecyclerView(mockCharacters);
    }

    @Test
    public void testCharactersNoDescriptionIsShown() {
        when(component.getMockWatchTowerService().getCharacter(anyInt()))
                .thenReturn(Observable.<AndroidBoilerplateService.CharacterResponse>empty());
        int[] characterIds = new int[1];
        characterIds[0] = 1009610;
        Character mockCharacter = MockModelsUtil.createMockCharacter(1009610);
        List<Character> mockCharacters = new ArrayList<>();
        mockCharacters.add(mockCharacter);
        stubMockPosts(characterIds, mockCharacters);
        main.launchActivity(null);
        checkPostsDisplayOnRecyclerView(mockCharacters);
    }

    @Test
    public void testClickOnCardOpensCharacterActivity() {
        int[] characterIds =
                InstrumentationRegistry.getTargetContext().getResources().getIntArray(com.hitherejoe.androidboilerplate.R.array.avengers);
        List<Character> mockCharacters = MockModelsUtil.createListOfMockCharacters(characterIds);
        stubMockPosts(characterIds, mockCharacters);
        main.launchActivity(null);
        onView(withText(mockCharacters.get(0).name))
                .perform(click());
        onView(withText(com.hitherejoe.androidboilerplate.R.string.text_lorem_ipsum))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testClickOnView() {
        when(component.getMockWatchTowerService().getCharacter(anyInt()))
                .thenReturn(Observable.<AndroidBoilerplateService.CharacterResponse>empty());
        int[] characterIds = new int[1];
        characterIds[0] = 1009610;
        Character mockCharacter = MockModelsUtil.createMockCharacter(1009610);
        List<Character> mockCharacters = new ArrayList<>();
        mockCharacters.add(mockCharacter);
        stubMockPosts(characterIds, mockCharacters);
        main.launchActivity(null);
        onView(withText("View"))
                .perform(click());
        onView(withText(com.hitherejoe.androidboilerplate.R.string.text_lorem_ipsum))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testClickOnCollections() {
        when(component.getMockWatchTowerService().getCharacter(anyInt()))
                .thenReturn(Observable.<AndroidBoilerplateService.CharacterResponse>empty());
        int[] characterIds = new int[1];
        characterIds[0] = 1009610;
        Character mockCharacter = MockModelsUtil.createMockCharacter(1009610);
        List<Character> mockCharacters = new ArrayList<>();
        mockCharacters.add(mockCharacter);
        stubMockPosts(characterIds, mockCharacters);
        main.launchActivity(null);
        onView(withText("Collections"))
                .perform(click());
        onView(withText("Comics"))
                .check(matches(isDisplayed()));
    }

    private void checkPostsDisplayOnRecyclerView(List<Character> postsToCheck) {
        for (int i = 0; i < postsToCheck.size(); i++) {
            onView(withId(com.hitherejoe.androidboilerplate.R.id.recycler_characters))
                    .perform(RecyclerViewActions.scrollToPosition(i));
            checkPostDisplays(postsToCheck.get(i));
        }
    }

    private void checkPostDisplays(Character character) {
        onView(withText(character.name))
                .check(matches(isDisplayed()));
        onView(withText(character.description))
                .check(matches(isDisplayed()));
    }

    private void stubMockPosts(int[] ids, List<Character> mockPosts) {
        for (int i = 0; i < mockPosts.size(); i++) {
            when(component.getMockWatchTowerService().getCharacter(ids[i]))
                    .thenReturn(Observable.just(MockModelsUtil.createMockCharacterResponse(mockPosts.get(i))));
        }
    }
}