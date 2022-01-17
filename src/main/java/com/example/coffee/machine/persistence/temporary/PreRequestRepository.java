package com.example.coffee.machine.persistence.temporary;

import java.time.Duration;

public interface PreRequestRepository {

    void save(String key, Object value, Duration duration);

    Object getByKeyAndDelete(String key);

}
