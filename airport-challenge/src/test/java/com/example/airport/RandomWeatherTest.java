package com.example.airport;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomWeatherTest {

    //changes made
    private static Random alwaysRolls(int value) {
        return new Random() {
            @Override
            public int nextInt(int bound) {
                return value;
            }
        };
    }

    @Test
    void isStormyWhenRollIsZero() {
        assertTrue(new RandomWeather(alwaysRolls(0)).isStormy());
    }

    @Test
    void isClearWhenRollIsNotZero() {
        assertFalse(new RandomWeather(alwaysRolls(1)).isStormy());
    }
}