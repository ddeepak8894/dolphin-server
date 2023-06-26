package com.dolphin.dtos;

import java.util.ArrayList;
import java.util.List;

import com.dolphin.entities.SensorLinker;

import lombok.Data;

@Data
public class SensorAddDto {
	
	private Integer sensorId;
	private Integer userId;
	private String nameOfSensor;
	private String currentStatus;
	
	public static List<SensorAddDto> getListFromSensorLinkerList(List<SensorLinker> linkerList ) {
		List<SensorAddDto> sensorList = new ArrayList();
		
		
		linkerList.forEach((s)->{
			SensorAddDto sensor = new SensorAddDto();
			sensor.setSensorId(s.getSensor().getSensorId());
			sensor.setNameOfSensor(s.getSensor().getNameOfSensor());
			sensor.setCurrentStatus(s.getSensor().getCurrentStatus());
			System.out.println("inside list ======="+sensor.toString());
			sensorList.add(sensor);
			
		});
		
		return sensorList;
	}
	
	

}
