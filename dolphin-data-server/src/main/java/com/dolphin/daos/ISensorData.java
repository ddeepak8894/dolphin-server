package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolphin.entities.SensorData;
import java.util.List;


public interface ISensorData extends JpaRepository<SensorData, Integer> {
	List<SensorData> findBySensor_SensorId(int sensor_SensorId);

}
