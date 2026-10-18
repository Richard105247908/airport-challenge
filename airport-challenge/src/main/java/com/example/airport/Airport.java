package com.example.airport;

// Deliberately incomplete: this compiles, but the first test is RED.
// Make it green with the smallest change you can, then refactor.
public class Airport {

    private final Plane [] planes=new Plane[5];
    private  int num=0;

    public void land(Plane plane) {
        planes[num++]=plane;
    }

    //Create a for loop to identify if plane has been added to array.
    public boolean hasPlane(Plane plane) {
        for (int i = 0; i < planes.length; i++) {
            if (planes[i]==plane){
                return true;
            }
        }
        return false;
    }
}
