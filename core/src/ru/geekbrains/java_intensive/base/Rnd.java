package ru.geekbrains.java_intensive.base;

import java.util.Random;

public class Rnd {

    private static final Random rnd = new Random();

    public static float nextFloat(float min, float max) {
        return rnd.nextFloat() * (max - min) + min;
    }
}
