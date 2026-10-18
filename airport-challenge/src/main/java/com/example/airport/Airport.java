package com.example.airport;

import java.util.ArrayList;
import java.util.List;

public class Airport {

    static final int DEFAULT_CAPACITY = 5;

    private final List<Plane> planes = new ArrayList<>();
    private final int capacity;
    private final Weather weather;


    public Airport(Weather weather) {
        this(weather, DEFAULT_CAPACITY);
    }

    public Airport(Weather weather, int capacity) {
        this.weather = weather;
        this.capacity = capacity;
    }

    public void land(Plane plane) {
        if (planes.size() >= capacity) {
            throw new IllegalStateException("Cannot land: airport is full");
        }
        if (weather.isStormy()){
            throw new IllegalStateException("Cannot land: stormy weather");
        }
        if (planes.contains(plane)){
            throw new IllegalStateException("Cannot land: plane is already at the airport");
        }
        planes.add(plane);
    }

    public boolean hasPlane(Plane plane) {
        return planes.contains(plane);
    }

    public void takeOff(Plane plane) {

        if (weather.isStormy()) {
            throw new IllegalStateException("Cannot take off: stormy weather");
        }
        planes.remove(plane);
    }

}
