package com.dolphin.dtos;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dolphin.entities.SensorData;

import lombok.Data;

@Data
public class SensorDataAddDto {
	
	private int userId;
	private int sensorId;
	private String data;
	private String maxValue;
	private String nameOfSensor;
	private String currentStatus;
	private Date lastUpdatedAt;
	
	public static List<SensorDataAddDto> createDataList(List<SensorData> data) {
	    List<SensorDataAddDto> list = new ArrayList<>();
	    Date now = new Date();

	    data.forEach(d -> {
	        if (now.getTime() - d.getLastUpdatedAt().getTime() <= 5 * 60 * 1000) {
	            SensorDataAddDto sensor = new SensorDataAddDto();
	            sensor.setSensorId(d.getSensor().getSensorId());
	            sensor.setData(d.getData());
	            sensor.setLastUpdatedAt(d.getLastUpdatedAt());
	            list.add(sensor);
	        }
	    });

	    return list;
	}


}
