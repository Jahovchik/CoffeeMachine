package com.example.coffee.machine.service;

import java.util.UUID;

public interface PreRequestService {

    UUID save(Object value);

    Object approve(UUID key);

}
