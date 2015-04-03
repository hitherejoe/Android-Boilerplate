package com.hitherejoe.androidboilerplate.util;

import com.hitherejoe.androidboilerplate.data.model.Boilerplate;

import java.util.Random;
import java.util.UUID;

public class MockModelsUtil {

    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    public static Boilerplate createMockBoilerPlate() {
        Boilerplate boilerplate = new Boilerplate();
        boilerplate.id = new Random().nextInt();
        boilerplate.androidBoilerplate = generateRandomString();
        return boilerplate;
    }

}