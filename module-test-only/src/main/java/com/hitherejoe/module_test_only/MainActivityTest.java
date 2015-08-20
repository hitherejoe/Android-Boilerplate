package com.hitherejoe.module_test_only;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.R;
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
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private Context mContext;

    @Rule
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<>(MainActivity.class, false, false);

    @Rule
    public final TestComponentRule component = new TestComponentRule();

    @Before
    public void initTargetContext() {
        mContext = getTargetContext();
    }

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
        int[] characterIds =
                InstrumentationRegistry.getTargetContext().getResources().getIntArray(com.hitherejoe.androidboilerplate.R.array.avengers);
        List<Character> mockCharacters = new ArrayList<>();
        Character mockCharacter = MockModelsUtil.createMockCharacter(characterIds[0]);
        mockCharacter.description = "";
        mockCharacters.add(mockCharacter);
        int[] ids = new int[1];
        ids[0] = mockCharacter.id;
        stubMockPosts(ids, mockCharacters);
        main.launchActivity(null);
        checkPostsDisplayOnRecyclerView(mockCharacters);
    }

    @Test
    public void testClickOnViewCharacter() {
        int[] characterIds =
                InstrumentationRegistry.getTargetContext().getResources().getIntArray(com.hitherejoe.androidboilerplate.R.array.avengers);
        List<Character> mockCharacters = new ArrayList<>();
        Character mockCharacter = MockModelsUtil.createMockCharacter(characterIds[0]);
        mockCharacter.description = "";
        mockCharacters.add(mockCharacter);
        AndroidBoilerplateService.CharacterResponse characterResponse = new AndroidBoilerplateService.CharacterResponse();
        AndroidBoilerplateService.Data data = new AndroidBoilerplateService.Data();
        data.results = mockCharacters;
        characterResponse.data = data;
        when(component.getMockWatchTowerService().getCharacter(characterIds[0]))
                .thenReturn(Observable.just(characterResponse));
        main.launchActivity(null);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("View"))
                .perform(click());
    }

    @Test
    public void testClickOnCollections() {
        int[] characterIds =
                InstrumentationRegistry.getTargetContext().getResources().getIntArray(com.hitherejoe.androidboilerplate.R.array.avengers);
        List<Character> mockCharacters = new ArrayList<>();
        Character mockCharacter = MockModelsUtil.createMockCharacter(characterIds[0]);
        mockCharacter.description = "";
        mockCharacters.add(mockCharacter);
        int[] ids = new int[1];
        ids[0] = mockCharacter.id;
        stubMockPosts(ids, mockCharacters);
        main.launchActivity(null);
        onView(withText("Collections"))
                .perform(click());
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
            AndroidBoilerplateService.CharacterResponse characterResponse = new AndroidBoilerplateService.CharacterResponse();
            List<Character> list = new ArrayList<>();
            list.add(mockPosts.get(i));
            AndroidBoilerplateService.Data data = new AndroidBoilerplateService.Data();
            data.results = list;
            characterResponse.data = data;
            when(component.getMockWatchTowerService().getCharacter(ids[i]))
                    .thenReturn(Observable.just(characterResponse));
        }
    }
}