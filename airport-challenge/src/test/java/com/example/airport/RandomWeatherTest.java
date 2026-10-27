package com.example.airport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomWeatherTest {

    @Mock
    Random random;

    @Test
    void isStormyWhenRollIsZero(){
        when(random.nextInt(RandomWeather.STORM_ODDS)).thenReturn(0);

        assertTrue(new RandomWeather(random).isStormy());
    }
    @Test
    void isClearWhenRollIsNotZero() {
        when(random.nextInt(RandomWeather.STORM_ODDS)).thenReturn(1);

        assertFalse(new RandomWeather(random).isStormy());
    }
}
