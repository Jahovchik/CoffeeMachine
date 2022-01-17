package com.example.coffee.machine.model.dto;

import com.example.coffee.machine.model.enums.EspressoBasedCoffeeType;
import com.example.coffee.machine.model.enums.GrindSize;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OrderDTO implements Serializable {

    @NotNull(message = "Coffee type cannot be null")
    private EspressoBasedCoffeeType coffeeType;

    private Boolean strong;

    private GrindSize grindSize;

    @Min(value = 88, message = "Temperature below 88 is not supported")
    @Max(value = 96, message = "Temperature above 96 is not supported")
    private Integer temperature;

    public OrderDTO() {
    }

    public EspressoBasedCoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(EspressoBasedCoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public Boolean getStrong() {
        return strong;
    }

    public void setStrong(Boolean strong) {
        this.strong = strong;
    }

    public GrindSize getGrindSize() {
        return grindSize;
    }

    public void setGrindSize(GrindSize grindSize) {
        this.grindSize = grindSize;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
}
