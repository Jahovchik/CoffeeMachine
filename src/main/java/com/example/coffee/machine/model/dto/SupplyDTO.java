package com.example.coffee.machine.model.dto;

import java.io.Serializable;

public class SupplyDTO implements Serializable {

    private int coffee;
    private int water;
    private int milk;

    public SupplyDTO() {
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }
}
