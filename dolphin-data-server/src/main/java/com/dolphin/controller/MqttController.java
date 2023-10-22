package com.dolphin.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import config.MqttBeans;
import config.MqttBeans.MyGateway;
import mqtt_handlers.MqttMessageRequest;
import mqtt_handlers.MqttPublisher;


@RestController
@RequestMapping("api/mqtt")
public class MqttController {

	@Autowired
	private MqttPublisher mqttPublisherService;
	@Autowired
	private MyGateway myGateway;
	
	

    @PostMapping("/publish")
    public String publishMessage(@RequestBody MqttMessageRequest request) {

        ObjectMapper objectMapper = new ObjectMapper();
        String messageJson="";
        try {
			 messageJson = objectMapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JsonObject convertObject = new Gson().fromJson(messageJson, JsonObject.class);
        System.out.println("=========convertObject "+convertObject);
        myGateway.sendToMqtt(request.getTopic(), convertObject.get("message").toString());
        
        return "Message sent to MQTT topic: " + request.getTopic();
    }
}
