package com.redlislearn2.RedisDemo2.config;

import com.redlislearn2.RedisDemo2.utils.MqttUtils;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLSocketFactory;

@Configuration
public class MqttConfig {

    @Value("${mqtt.broker}")
    private String broker;

    @Value("${mqtt.clientId}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Bean
    public MqttClient mqttClient(MqttCallback mqttCallback) throws MqttException {

        MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());

        // ✅ Only apply SSL socket if broker uses ssl
        if (broker.startsWith("ssl://")) {
            try {
                SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                options.setSocketFactory(MqttUtils.getSSLSocketFactory());
            } catch (Exception e) {
                throw new RuntimeException("Failed to set SSL socket factory", e);
            }
        }

        client.setCallback(mqttCallback);
        client.connect(options);
        client.subscribe(topic);

        return client;
    }

}
