package com.example.coffee.machine.web.exception;

public class NoSuchRequestException extends RuntimeException {

    public NoSuchRequestException(String message) {
        super(message);
    }
}
