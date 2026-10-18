package com.example.airport;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AirportTest {

    // Test #1 (RED): a plane that has been told to land is in the airport.
    @Test
    void landedPlaneIsInAirport() {
        Airport airport = new Airport();
        Plane plane = new Plane();

        airport.land(plane);

        assertTrue(airport.hasPlane(plane));
    }

    @Test
    void unlandedPlaneIsNotInAirport(){
        Airport airport = new Airport();
        Plane plane = new Plane();

        assertFalse(airport.hasPlane(plane));
    }
}
