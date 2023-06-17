package com.dolphin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.dtos.Response;
import com.dolphin.dtos.SensorAddDto;
import com.dolphin.dtos.SensorDataAddDto;
import com.dolphin.entities.Sensor;
import com.dolphin.services.SensorService;

@CrossOrigin("*")
@RestController @RequestMapping("/api/sensor/")
public class SensorController {
	
	@Autowired
	SensorService sensorService;
	
	@PostMapping("/addSensorData")
	public ResponseEntity<?> addSensorData(@RequestBody SensorDataAddDto data){
		
		
		return Response.error(sensorService.addSensorData(data));
	}
	
	@PostMapping("/addSensor")
	public ResponseEntity<?> addSensor(@RequestBody SensorAddDto data){
		
		
		
		return Response.error(sensorService.addSensor(data));
	}

}
