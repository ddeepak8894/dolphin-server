package mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import com.dolphin.services.SensorService;

@MessageEndpoint
public class MqttMessageHandler {
	@Autowired
	SensorService sensorService;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<?> message) {
        String payload = message.getPayload().toString();
        System.out.println("Received MQTT message MqttMessageHandler: " + payload);
        
        // Handle the MQTT message as needed
        
    }
}

