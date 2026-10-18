package com.example.airport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    Airport airport;
    Plane plane;

    @BeforeEach
    void setUp() {
        airport = new Airport();
        plane = new Plane();
    }

    @Test
    void landedPlaneIsInAirport() {

        airport.land(plane);

        assertTrue(airport.hasPlane(plane));
    }

    @Test
    void unlandedPlaneIsNotInAirport() {

        assertFalse(airport.hasPlane(plane));
    }

    @Test
    void takenOffPlaneIsNoLongerInAirport() {

        airport.land(plane);
        airport.takeOff(plane);

        assertFalse(airport.hasPlane(plane));
    }

    @Test
    void fullAirportRejectsLanding() {
        Airport smallAirport = new Airport(1);
        smallAirport.land(plane);

        var ex = assertThrows(IllegalStateException.class,
                () -> smallAirport.land(new Plane()));

        assertEquals("Cannot land: airport is full", ex.getMessage());
    }

    @Test
    void airportWithNoCapacityGivenUsesDefault() {
                for (int i = 0; i < Airport.DEFAULT_CAPACITY; i++) {
            airport.land(new Plane());
        }

        assertThrows(IllegalStateException.class,
                () -> airport.land(new Plane()));
    }
}
