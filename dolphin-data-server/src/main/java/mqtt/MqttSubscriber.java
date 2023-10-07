package mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.dolphin.dtos.SensorDataAddDto;
import com.dolphin.services.SensorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MqttSubscriber {
	@Autowired
	SensorService sensorService;
	
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<String> message) {
        String topic = message.getHeaders().get("mqtt_receivedTopic", String.class);
        String payload = message.getPayload();
        SensorDataAddDto data = new SensorDataAddDto();
        data.setNameOfSensor(topic);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			data=objectMapper.readValue(payload, SensorDataAddDto.class);
			data.setNameOfSensor(topic);
			System.out.println("maaper work ======= "+data.toString());
			System.out.println(sensorService.addSensorData(data));;

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Received message from topic MqttSubscriber: " + topic);
        System.out.println("Payload: " + payload);
    }
}
