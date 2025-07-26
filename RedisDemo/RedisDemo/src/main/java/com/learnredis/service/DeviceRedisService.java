package com.learnredis.service;

import com.learnredis.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeviceRedisService {

    private static final String REDIS_KEY = "device:";

    private final RedisTemplate<String, Device> redisTemplate;

    public DeviceRedisService(RedisTemplate<String, Device> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public void saveDevice(Device device) {
        redisTemplate.opsForValue().set("device:" + device.getId(), device);
    }


    public void deleteDevice(Long deviceId) {
        redisTemplate.delete(REDIS_KEY + deviceId);
    }

}
