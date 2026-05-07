package utils;

import java.util.Random;

public class FakerUtils {

    public static String generateEmail() {
        return "user" + System.currentTimeMillis() + "@gmail.com";
    }

    public static String generateName() {
        return "User_" + new Random().nextInt(1000);
    }
}