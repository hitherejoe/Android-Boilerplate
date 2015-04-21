package com.hitherejoe.androidboilerplate.util;

import com.hitherejoe.androidboilerplate.data.model.Boilerplate;
import com.hitherejoe.androidboilerplate.data.model.Ribot;

import java.util.Random;
import java.util.UUID;

public class MockModelsUtil {

    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    public static Ribot createRibot() {
        Ribot ribot = new Ribot();
        ribot.info = new Ribot.Info();
        ribot.id = generateRandomString();
        ribot.hexCode = "#f49637";
        ribot.info.firstName = "Antony";
        ribot.info.lastName = "Ribot";
        ribot.info.role = "CEO";
        return ribot;
    }

}