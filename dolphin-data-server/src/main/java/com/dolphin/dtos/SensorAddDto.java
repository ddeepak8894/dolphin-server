package com.dolphin.dtos;

import lombok.Data;

@Data
public class SensorAddDto {
	
	
	private Integer userId;
	private String nameOfSensor;
	private String currentStatus;

}
