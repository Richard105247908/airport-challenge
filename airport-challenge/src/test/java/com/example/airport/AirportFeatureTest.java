package com.example.airport;

import org.junit.jupiter.api.Test;

public class AirportFeatureTest {

    @Test
    void landsAndTakesOffSeveralPlanesInClearWeather(){
        Weather clear = () -> false;
        Airport airport = new Airport(clear, 3);
        Plane first = new Plane();
        Plane second = new Plane();
        Plane third = new Plane();

        
    }
}
