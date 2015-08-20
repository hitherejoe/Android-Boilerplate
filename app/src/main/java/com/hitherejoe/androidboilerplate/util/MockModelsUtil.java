package com.hitherejoe.androidboilerplate.util;

import android.util.Log;

import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.data.model.Collection;
import com.hitherejoe.androidboilerplate.data.model.Item;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService;
import com.hitherejoe.androidboilerplate.data.remote.AndroidBoilerplateService.CharacterResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockModelsUtil {

    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    public static Character createMockCharacter(int id) {
        Character character = new Character();
        character.id = id;
        character.name = generateRandomString();
        character.description = id + character.name;
        character.thumbnail = new Character.Thumbnail();
        character.thumbnail.path = generateRandomString();
        character.thumbnail.extension = "png";
        character.events = createMockCollection(5);
        character.comics = createMockCollection(5);
        Log.e("SIZEDDDD", character.comics.items.size() + "");
        character.series = createMockCollection(5);
        character.stories = createMockCollection(5);
        return character;
    }

    public static Item createMockItem() {
        Item item = new Item();
        item.name = generateRandomString();
        return item;
    }

    public static Collection createMockCollection(int count) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            items.add(createMockItem());
        }
        Collection collection = new Collection();
        collection.items = items;
        return collection;
    }

    public static List<Character> createListOfMockCharacters(int[] ids) {
        List<Character> characters = new ArrayList<>();
        for (int id : ids) {
            characters.add(createMockCharacter(id));
        }
        return characters;
    }

    public static CharacterResponse createMockCharacterResponse(Character character) {
        AndroidBoilerplateService.CharacterResponse characterResponse = new AndroidBoilerplateService.CharacterResponse();
        List<Character> list = new ArrayList<>();
        list.add(character);
        AndroidBoilerplateService.Data data = new AndroidBoilerplateService.Data();
        data.results = list;
        characterResponse.data = data;
        return characterResponse;
    }

}