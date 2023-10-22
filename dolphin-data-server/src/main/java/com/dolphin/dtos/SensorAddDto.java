package com.dolphin.dtos;

import java.util.ArrayList;
import java.util.List;

import com.dolphin.entities.SensorLinker;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data @JsonIgnoreProperties
public class SensorAddDto {
	
	private Integer sensorId;
	private Integer userId;
	private String nameOfSensor;
	private String currentStatus;
	private String longitude;
	private String latitude;
	private Double longitudeLong;
	private Double latitudeLong;
	private String type;
	public static List<SensorAddDto> getListFromSensorLinkerList(List<SensorLinker> linkerList ) {
		List<SensorAddDto> sensorList = new ArrayList();
		
		
		linkerList.forEach((s)->{
			SensorAddDto sensor = new SensorAddDto();
			sensor.setSensorId(s.getSensor().getSensorId());
			sensor.setNameOfSensor(s.getSensor().getNameOfSensor());
			sensor.setCurrentStatus(s.getSensor().getCurrentStatus());
			sensor.setLatitude(s.getSensor().getLatitude());
			sensor.setLongitude(s.getSensor().getLongitude());
			sensor.setType(s.getSensor().getType());
			System.out.println("inside list ======="+sensor.toString());
			sensorList.add(sensor);
			
		});
		
		return sensorList;
	}
	
	

}
