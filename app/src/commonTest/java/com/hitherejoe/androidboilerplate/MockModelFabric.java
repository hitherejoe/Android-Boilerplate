package com.hitherejoe.androidboilerplate;

import com.hitherejoe.androidboilerplate.data.model.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockModelFabric {

    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    public static Character makeMockCharacter() {
        Character character = new Character();
        character.name = generateRandomString();
        character.films = makeMockCollection(5);
        character.species = makeMockCollection(5);
        character.species = makeMockCollection(5);
        character.species = makeMockCollection(5);
        character.vehicles = makeMockCollection(5);
        character.starships = makeMockCollection(5);
        return character;
    }

    public static List<String> makeMockCollection(int count) {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            items.add(generateRandomString());
        }
        return items;
    }

    public static List<Character> makeListOfMockCharacters(int count) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            characters.add(makeMockCharacter());
        }
        return characters;
    }

}
