package com.redlislearn2.RedisDemo2.service;

import com.redlislearn2.RedisDemo2.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeviceRedisService {

    private final RedisTemplate<String, Device> redisTemplate;
    private final static String PREFIX = "DEVICE:";

    @Autowired
    public DeviceRedisService(RedisTemplate<String, Device> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveDevice(Device device){
        String key = PREFIX + device.getId();
        redisTemplate.opsForValue().set(key,device);
    }

    public Device getDeviceById(Long id){
        String key = PREFIX + id;
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteDevice(Long id){
        String key = PREFIX + id;
        redisTemplate.delete(key);
    }

}
//🔄 Summary of the Flow:
//Map your JSON into a Java Device object using the POJO.
//
//Connect Spring Boot to Redis using RedisTemplate.
//
//Serialize your object using Jackson.
//
//Store/retrieve using .opsForValue().