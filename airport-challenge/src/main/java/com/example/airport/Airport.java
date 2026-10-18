package com.example.airport;

import java.util.ArrayList;
import java.util.List;

// Make it green with the smallest change you can, then refactor.
public class Airport {

    private int capacity;

    Airport(){
        this(5);
    }
    Airport(int capacity){
        this.capacity=capacity;
        }

    private final List<Plane> planes=new ArrayList<>();

    public void land(Plane plane) {
        if (planes.size()>=capacity){
            throw new IllegalStateException("Cannot land: airport is full");
        }
        planes.add(plane);
    }

    public boolean hasPlane(Plane plane) {
        return planes.contains(plane);
    }

    public void takeOff(Plane plane){

            planes.remove(plane);
    }

}
