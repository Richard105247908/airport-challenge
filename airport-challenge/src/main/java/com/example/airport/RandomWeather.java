package com.example.airport;

import java.util.Random;

public class RandomWeather {

    static final int STORM_ODDS = 4;
    private final Random random;

    public RandomWeather(Random random) {
        this.random = random;
    }
}
