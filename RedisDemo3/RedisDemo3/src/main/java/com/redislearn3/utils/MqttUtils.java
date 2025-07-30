package com.redislearn3.utils;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;

public class MqttUtils {

    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        // Create SSL context with TLS protocol
        SSLContext context = SSLContext.getInstance("TLS");

        // Get default trust managers
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init((KeyStore) null);  // Use default system truststore

        // Initialize SSL context with trust managers
        context.init(null, tmf.getTrustManagers(), null);

        // Return the socket factory
        return context.getSocketFactory();
    }
}
