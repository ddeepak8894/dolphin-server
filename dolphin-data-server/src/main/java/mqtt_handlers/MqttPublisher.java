package mqtt_handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.support.MessageBuilder;

@Component
public class MqttPublisher {
    @Autowired
    private MessageChannel mqttOutboundChannel;

    public void publishMessage( String messagePayload,String topic) {
    	System.out.println("in publication----------------");
        Message<String> message = MessageBuilder
                .withPayload(messagePayload)
                .setHeader("mqtt_topic", topic) // Set the MQTT topic as a header
                .build();
        mqttOutboundChannel.send(message);
    }
}
