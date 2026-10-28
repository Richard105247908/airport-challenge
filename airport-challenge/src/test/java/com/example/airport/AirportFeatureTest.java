package com.example.airport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AirportFeatureTest {

    @Test
    void landsAndTakesOffSeveralPlanesInClearWeather(){
        Weather clear = () -> false;
        Airport airport = new Airport(clear, 3);
        Plane first = new Plane();
        Plane second = new Plane();
        Plane third = new Plane();

        airport.land(first);
        airport.land(second);
        airport.land(third);
        assertThrows(IllegalStateException.class, () -> airport.land(new Plane()));


    }
}
