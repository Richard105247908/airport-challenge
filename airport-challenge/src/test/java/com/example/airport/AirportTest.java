package com.example.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirportTest {


    @Mock Weather weather;
    Airport airport;
    Plane plane;

    @BeforeEach
    void setUp() {
        airport = new Airport(weather);
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
        Airport smallAirport = new Airport(weather,1);
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


    @Test
    void stormyWeatherBlocksTakeOff() {
        airport.land(plane);
        when(weather.isStormy()).thenReturn(true);

        var ex = assertThrows(IllegalStateException.class,
                () -> airport.takeOff(plane));

        assertEquals("Cannot take off: stormy weather", ex.getMessage());
    }
    @Test
    void stormyWeatherBlocksLanding() {
        when(weather.isStormy()).thenReturn(true);

        var ex = assertThrows(IllegalStateException.class,
                () -> airport.land(plane));

        assertEquals("Cannot land: stormy weather", ex.getMessage());
    }
    @Test void planeAlreadyLandedCannotLandAgain() {
        airport.land(plane);
    }
}
