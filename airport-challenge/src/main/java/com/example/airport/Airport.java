package com.example.airport;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    static final int DEFAULT_CAPCITY = 5;

    private final List<Plane> planes=new ArrayList<>();
    private final int capacity;

    public Airport(){
        this(DEFAULT_CAPCITY);
       }

    public Airport(int capacity){
        this.capacity=capacity;
        }
        
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
