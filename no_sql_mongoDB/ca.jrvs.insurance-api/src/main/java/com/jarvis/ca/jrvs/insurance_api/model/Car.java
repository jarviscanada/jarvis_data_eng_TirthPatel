package com.jarvis.ca.jrvs.insurance_api.model;

public class Car {
    private String make;
    private String model;
    private int maxSpeed;

    public Car(){}
    public Car(String make, String model, int maxSpeed) {
        this.make = make;
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
