import json
import time
import random
import ssl
from datetime import datetime
import paho.mqtt.client as mqtt

# MQTT Broker Configuration
MQTT_BROKER = "51f61e2c914643308041f3b4e1ee3e12.s1.eu.hivemq.cloud"
MQTT_PORT = 8883
MQTT_TOPIC = "device/data"
MQTT_USERNAME = "chiragdeshmukh"
MQTT_PASSWORD = "Chirag00728@"

# Track connection status
is_connected = False

def on_connect(client, userdata, flags, rc):
    global is_connected
    if rc == 0:
        is_connected = True
        print("✅ Connected to HiveMQ broker!")
    else:
        print(f"❌ Failed to connect. Return code: {rc}")

def on_log(client, userdata, level, buf):
    print("Log:", buf)

# Create MQTT client
client = mqtt.Client()

# Attach callbacks
client.on_connect = on_connect
client.on_log = on_log

# Enable SSL/TLS
client.tls_set(tls_version=ssl.PROTOCOL_TLS)

# Set credentials
client.username_pw_set(MQTT_USERNAME, MQTT_PASSWORD)

# Connect and wait until connected
client.connect(MQTT_BROKER, MQTT_PORT)
client.loop_start()

# Wait for max 10 seconds for connection
for i in range(10):
    if is_connected:
        break
    time.sleep(1)

if not is_connected:
    print("❌ Could not connect to HiveMQ broker. Exiting.")
    client.loop_stop()
    exit()

# Start sending data
print("🚀 Starting to send data...")

total_consumption = 200.0

while True:
    current_consumption = round(random.uniform(15.0, 30.0), 2)
    voltage = round(random.uniform(220, 240), 1)
    current = round(current_consumption / voltage, 2)
    total_consumption += current_consumption * (3 / 3600)

    payload = {
        "id": 1,
        "name": "Smart Fan",
        "type": "Actuator",
        "status": "ACTIVE",
        "location": "Living Room",
        "currentConsumption": current_consumption,
        "totalConsumption": round(total_consumption, 2),
        "lastSyncTime": datetime.now().isoformat(timespec='milliseconds'),
        "ipAddress": "192.168.0.102",
        "controllable": True,
        "voltage": voltage,
        "current": current
    }

    print("📤 Sending:", payload)
    client.publish(MQTT_TOPIC, json.dumps(payload), qos=1)
    time.sleep(3)
