package com.example.airport;

import java.util.Random;

public class RandomWeather implements Weather{

    static final int STORM_ODDS = 4;
    private final Random random;

    public RandomWeather(Random random) {
        this.random = random;
    }
    public RandomWeather(){
        this(new Random());
    }
    public boolean isStormy(){
        return random.nextInt(STORM_ODDS)==0;
    }
}
