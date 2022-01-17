package com.example.coffee.machine.model.enums;

public enum EspressoBasedCoffeeType{
    ESPRESSO(1, 0, 0, 'E'),
    DOPPIO(2, 0, 0, 'D'),
    CAPPUCCINO(2, 0, 120, 'C'),
    AMERICANO(2, 120, 0, 'A'),
    LATTE(2, 0, 240, 'L');

    private final int espressoShots;
    private final int additionalWater;
    private final int milkVolume;

    private final Character code;

    private EspressoBasedCoffeeType(int shots, int water, int milk, Character code) {
        this.espressoShots = shots;
        this.additionalWater = water;
        this.milkVolume = milk;
        this.code = code;
    }

    public int getEspressoShots() {
        return espressoShots;
    }

    public int getAdditionalWater() {
        return additionalWater;
    }

    public int getMilkVolume() {
        return milkVolume;
    }

    public Character getCode() {
        return code;
    }
}
