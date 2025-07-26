package com.learnredis.model;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@RedisHash("Device")
public class Device implements Serializable {

    private Long id;
    private String name;
    private String type;
    private String status;
    private String location;
    private double currentConsumption;
    private double totalConsumption;
    private String firmwareVersion;
    private LocalDateTime lastSyncTime;
    private String ipAddress;
    private boolean controllable;
    private String manufacturer;
    private String model;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double voltage;
    private double current;

    public Device() {
    }

    public Device(Long id, String name, String type, String status, String location, double currentConsumption, double totalConsumption, String firmwareVersion, LocalDateTime lastSyncTime, String ipAddress, boolean controllable, String manufacturer, String model, LocalDateTime createdAt, LocalDateTime updatedAt, double voltage, double current) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.location = location;
        this.currentConsumption = currentConsumption;
        this.totalConsumption = totalConsumption;
        this.firmwareVersion = firmwareVersion;
        this.lastSyncTime = lastSyncTime;
        this.ipAddress = ipAddress;
        this.controllable = controllable;
        this.manufacturer = manufacturer;
        this.model = model;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.voltage = voltage;
        this.current = current;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getCurrentConsumption() {
        return currentConsumption;
    }

    public void setCurrentConsumption(double currentConsumption) {
        this.currentConsumption = currentConsumption;
    }

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public LocalDateTime getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(LocalDateTime lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isControllable() {
        return controllable;
    }

    public void setControllable(boolean controllable) {
        this.controllable = controllable;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }
}
