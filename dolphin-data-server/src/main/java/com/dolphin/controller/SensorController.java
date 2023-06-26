package com.dolphin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.dtos.Response;
import com.dolphin.dtos.SensorAddDto;
import com.dolphin.dtos.SensorDataAddDto;
import com.dolphin.entities.DataTable;
import com.dolphin.entities.Sensor;
import com.dolphin.entities.SensorData;
import com.dolphin.services.SensorService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/sensor/")
public class SensorController {

	@Autowired
	SensorService sensorService;

	@PostMapping("/addSensorData")
	public ResponseEntity<?> addSensorData(@RequestBody SensorDataAddDto data) {

		return Response.error(sensorService.addSensorData(data));
	}

	@PostMapping("/addSensor")
	public ResponseEntity<?> addSensor(@RequestBody SensorAddDto data) {

		return Response.success(sensorService.addSensor(data));
	}

	@GetMapping("/getSensorsOfUser/{id}")
	public ResponseEntity<?> getAllSensorsOfUser(@PathVariable int id) {

		List<SensorAddDto> sensorList = sensorService.getAllSensorsOfUser(id);

		if (sensorList == null) {
			return Response.error("SENSOR_LIST_IS_EMPTY");
		} else {
			return Response.success(sensorList);
		}

	}
	
	@PostMapping("/getSensorData")
	public ResponseEntity<?> getSensorData(@RequestBody SensorAddDto data) {
		System.out.println("to get data of sensor ====== "+data.getSensorId());
		List<SensorDataAddDto> datas=sensorService.getDataOfSensorByID(data.getSensorId());
		if(datas != null) {
			System.out.println(datas.toString());
			return  Response.success(datas);
		}else {
			return  Response.error("SENSOR_DOES_NOT_EXIST");
		}
		
	}

}
