package com.example.airport;

import java.util.Random;

public class RandomWeather {

    static final int STORM_ODDS = 4;
    private final Random random;

    public RandomWeather(Random random) {
        this.random = random;
    }
    public RandomWeather(){
        this(new Random());
    }
    int isStormy(){
        return random.nextInt(STORM_ODDS)==0;
    }
}
