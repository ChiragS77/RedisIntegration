package com.learnredis.controller;

import com.learnredis.model.Device;
import com.learnredis.service.DeviceRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceRedisController {

    @Autowired
    private DeviceRedisService deviceRedisService;

    @PostMapping("/create")
    public ResponseEntity<String> cacheDevice(@RequestBody Device device) {
        deviceRedisService.saveDevice(device);
        return ResponseEntity.ok("Device cached in Redis");
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Device> getCachedDevice(@PathVariable Long id) {
//        Device device = deviceRedisService.getDevice(id);
//        return ResponseEntity.ok(device);
//    }

}
