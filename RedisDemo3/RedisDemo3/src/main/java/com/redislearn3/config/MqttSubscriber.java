package com.redislearn3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redislearn3.model.Device;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber implements MqttCallback {


    @Autowired
    private RedisTemplate<String, Device> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("MQTT connection lost: "+throwable.getMessage());

    }



    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("Received Message: " + mqttMessage);

        try {
            String payload = new String(mqttMessage.getPayload());

            // ✅ Use Spring-configured ObjectMapper
            Device device = objectMapper.readValue(payload, Device.class);
            String key = "DEVICE:" + device.getId();

            redisTemplate.opsForValue().set(key, device);
            System.out.println("Stored in Redis under key: " + key);

        } catch (Exception e) {
            e.printStackTrace(); // for debugging
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
