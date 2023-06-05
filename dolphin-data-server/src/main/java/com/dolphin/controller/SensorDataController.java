package com.dolphin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.dtos.Response;
import com.dolphin.dtos.SensorData;
import com.dolphin.entities.DataTable;
import com.dolphin.services.DataTableServices;

@CrossOrigin("*")
@RestController @RequestMapping("/api/sensorDataStore")
public class SensorDataController {
	
	@Autowired
	DataTableServices dataTableService;
	
	@GetMapping("/getSensorDetails")
	public ResponseEntity<?> getSensorDetails() {
		List<DataTable> datas=dataTableService.getAllData();
		return  Response.success(datas);
	}
	
	@PostMapping("/writeSensorData")
	public ResponseEntity<?> writeSensorData(@RequestBody DataTable sensorData ) {
		dataTableService.addData(sensorData);
		return Response.error(sensorData);
		
	}

}
