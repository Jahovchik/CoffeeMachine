package com.example.coffee.machine.model.enums;

public enum GrindSize {
    COARSE('C'),
    MEDIUM('M'),
    FINE('F');

    private final Character code;

    GrindSize(Character code) {
        this.code = code;
    }

    public Character getCode() {
        return code;
    }
}
