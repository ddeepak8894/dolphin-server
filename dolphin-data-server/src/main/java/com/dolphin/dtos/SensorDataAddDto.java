package com.dolphin.dtos;

import lombok.Data;

@Data
public class SensorDataAddDto {
	
	private int userId;
	private int sensorId;
	private String data;

}
