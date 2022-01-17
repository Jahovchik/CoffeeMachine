package com.example.coffee.machine.persistence.temporary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class PreRequestRepositoryImpl implements PreRequestRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public PreRequestRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, Object value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    public Object getByKeyAndDelete(String key) {
        return redisTemplate.opsForValue().getAndDelete(key);
    }

}
