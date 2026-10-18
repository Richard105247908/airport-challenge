package com.example.airport;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AirportTest {

    Airport airport;
    Plane plane;

   @BeforeEach
   void setUp(){
       airport = new Airport();
       plane=new Plane();
   }

    @Test
    void landedPlaneIsInAirport() {

        airport.land(plane);

        assertTrue(airport.hasPlane(plane));
    }

    @Test
    void unlandedPlaneIsNotInAirport(){

        assertFalse(airport.hasPlane(plane));
    }

    @Test
    void takenOffPlaneIsNoLongerInAirport(){

        airport.land(plane);
        airport.takeOff(plane);

        assertFalse(airport.hasPlane(plane));
    }
}
