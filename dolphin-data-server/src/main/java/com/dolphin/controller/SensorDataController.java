package com.dolphin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.dtos.Response;
import com.dolphin.dtos.SensorData;

@RestController @RequestMapping("/api/sensorDataStore")
public class SensorDataController {
	
	@GetMapping("/getSensorDetails")
	public String getSensorDetails() {
		return  "Welcome to Sensor Data Interface";
	}
	
	@PostMapping("/writeSensorData")
	public ResponseEntity<?> writeSensorData(@RequestBody SensorData sensorData ) {
		
		return Response.success(sensorData);
		
	}

}
