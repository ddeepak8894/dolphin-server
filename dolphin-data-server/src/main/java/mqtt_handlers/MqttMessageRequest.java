package mqtt_handlers;

import lombok.Data;

@Data
public class MqttMessageRequest {
    private String topic;
    private String message;
}
