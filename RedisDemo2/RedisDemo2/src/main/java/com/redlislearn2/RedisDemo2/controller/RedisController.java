package com.redlislearn2.RedisDemo2.controller;

import com.redlislearn2.RedisDemo2.model.Device;
import com.redlislearn2.RedisDemo2.service.DeviceRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String, Device> redisTemplate;

    @Autowired
    private DeviceRedisService deviceRedisService;

    private static final String PREFIX = "DEVICE:";

    @PostMapping("/save")
    public ResponseEntity<String>  saveDevice(@RequestBody Device device){
        this.deviceRedisService.saveDevice(device);
        return ResponseEntity.ok("Saved Successs");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable Long id) {
        String key = PREFIX + id;
        Device device = redisTemplate.opsForValue().get(key);
        if (device != null) {
            return ResponseEntity.ok(device);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}


