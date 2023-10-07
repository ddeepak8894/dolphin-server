package com.dolphin.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import config.MqttBeans;
import config.MqttBeans.MyGateway;
import mqtt_handlers.IMqttPublisher;
import mqtt_handlers.MqttPublisher;


@RestController
@RequestMapping("api/mqtt")
public class MqttController {

	@Autowired
	private MqttPublisher mqttPublisherService;
	@Autowired
	private MyGateway myGateway;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody String request) {
    	JsonObject  convertObject = new Gson().fromJson(request, JsonObject.class);
    	System.out.println(convertObject.toString());
    	myGateway.sendToMqtt(convertObject.get("message").toString());
    	mqttPublisherService.publishMessage(convertObject.get("message").toString(), convertObject.get("topic").toString());
    	
   
    	return "success";
    }
}