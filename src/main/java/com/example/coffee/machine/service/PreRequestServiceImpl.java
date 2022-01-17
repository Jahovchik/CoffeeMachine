package com.example.coffee.machine.service;

import com.example.coffee.machine.persistence.temporary.PreRequestRepository;
import com.example.coffee.machine.web.exception.NoSuchRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
public class PreRequestServiceImpl implements PreRequestService {

    private static final int EXPIRATION_IN_MINUTES = 1;

    private final Duration expirationTimeout = Duration.ofMinutes(EXPIRATION_IN_MINUTES);
    private final PreRequestRepository preRequestRepository;

    @Autowired
    public PreRequestServiceImpl(PreRequestRepository preRequestRepository) {
        this.preRequestRepository = preRequestRepository;
    }

    public UUID save(Object value) {
        UUID key = UUID.randomUUID();
        preRequestRepository.save(key.toString(), value, expirationTimeout);
        return key;
    }

    public Object approve(UUID key) {
        Object request = preRequestRepository.getByKeyAndDelete(key.toString());
        if (request == null) {
            throw new NoSuchRequestException("Request not found. Unable to approve.");
        }
        return request;
    }

}
