package com.example.airport;

import java.util.ArrayList;
import java.util.List;

// Make it green with the smallest change you can, then refactor.
public class Airport {

    private final List<Plane> planes=new ArrayList<>();

    public void land(Plane plane) {
        planes.add(plane);
    }

    public boolean hasPlane(Plane plane) {
        return planes.contains(plane);
    }

    public void takeOff(Plane plane){

            planes.remove(plane);
    }
}
