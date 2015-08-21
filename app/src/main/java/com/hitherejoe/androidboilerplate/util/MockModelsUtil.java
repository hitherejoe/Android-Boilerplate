package com.hitherejoe.androidboilerplate.util;

import com.hitherejoe.androidboilerplate.data.model.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockModelsUtil {

    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    public static Character createMockCharacter() {
        Character character = new Character();
        character.name = generateRandomString();
        character.films = createMockCollection(5);
        character.species = createMockCollection(5);
        character.species = createMockCollection(5);
        character.species = createMockCollection(5);
        character.vehicles = createMockCollection(5);
        character.starships = createMockCollection(5);
        return character;
    }

    public static List<String> createMockCollection(int count) {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            items.add(generateRandomString());
        }
        return items;
    }

    public static List<Character> createListOfMockCharacters(int count) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            characters.add(createMockCharacter());
        }
        return characters;
    }

}