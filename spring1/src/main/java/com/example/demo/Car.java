package com.example.demo;

// this is just for fun, not related to the test!!!
// fluent
public class Car {

    public int engine;

    public Car setKm(int km)
    {
        engine = km;
        return this;
    }

    public Car drive(int km) {
        engine += km;
        return this;
    }

    public Car reduceKm(int km) {
        engine -= km;
        return this;
    }
}
