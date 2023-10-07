package mqtt_handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import com.dolphin.dtos.SensorDataAddDto;
import com.dolphin.services.SensorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class MqttSubscriber {
    @Autowired
    private SensorService sensorService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<SensorDataAddDto> batch = new ArrayList<>();
    private static final int BATCH_SIZE = 100; // Define your batch size

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<String> message) {
        String topic = message.getHeaders().get("mqtt_receivedTopic", String.class);
        String payload = message.getPayload();

        try {
            SensorDataAddDto data = objectMapper.readValue(payload, SensorDataAddDto.class);
            data.setNameOfSensor(topic);

            batch.add(data);

            if (batch.size() >= BATCH_SIZE) {
                // Process and insert the batch
                processAndInsertBatch();
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., log, report, or take corrective actions)
            e.printStackTrace();
        }

        System.out.println("Received message from topic MqttSubscriber: " + topic);
        System.out.println("Payload: " + payload);
    }

    private void processAndInsertBatch() {
        if (!batch.isEmpty()) {
            try {
                // Insert the batch of sensor data into the database
                sensorService.addSensorDataInBatch(batch);
                System.out.println("Batch insertion completed.");

                // Clear the batch for the next set of records
                batch.clear();
            } catch (Exception e) {
                // Handle exceptions that may occur during insertion
                e.printStackTrace();
            }
        }
    }
}
