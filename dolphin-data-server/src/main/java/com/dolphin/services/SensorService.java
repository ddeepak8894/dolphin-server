package com.dolphin.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolphin.daos.ISensorDao;
import com.dolphin.daos.ISensorData;
import com.dolphin.daos.ISensorLinkerDao;
import com.dolphin.daos.IUserDao;
import com.dolphin.dtos.SensorAddDto;
import com.dolphin.dtos.SensorDataAddDto;
import com.dolphin.entities.Sensor;
import com.dolphin.entities.SensorData;
import com.dolphin.entities.SensorLinker;
import com.dolphin.entities.User;

import jakarta.transaction.Transactional;

@Service@Transactional
public class SensorService {
	@Autowired
	IUserDao userDao;
	@Autowired
	ISensorDao sensorDao;
	@Autowired
	ISensorData sensorDataDao;
	@Autowired
	ISensorLinkerDao sensorDataLinkerDao;
	
	public String addSensorData(SensorDataAddDto data) {
		
		if(sensorDao.findById(data.getSensorId()).isPresent()&&userDao.findById(data.getUserId()).isPresent()) {
			System.out.println("sensor present or not=== ");
			Optional<Sensor> sensorOptional = sensorDao.findById(data.getSensorId());
			System.out.println("---------sensorOptional-"+sensorOptional.orElse(null)+data.toString());
			SensorData sensorData = new SensorData();
			sensorData.setData(data.getData().toString());
			sensorData.setSensor(sensorOptional.orElse(null));
			sensorData.setLastUpdatedAt(new Date());
			sensorDataDao.save(sensorData);
			
			return "data added successfully";
		}else {
			return "failed !!!!";
		}



	}
	
	
	public String addSensor(SensorAddDto sensorAddData) {
		
		if(userDao.findById(sensorAddData.getUserId()).isPresent()) {
			Sensor sensor = new Sensor();
			sensor.setNameOfSensor(sensorAddData.getNameOfSensor());
			sensor.setCurrentStatus(sensorAddData.getCurrentStatus());
			sensor.setLastUpdatedAt(new Date());
			Sensor savedSensor = sensorDao.save(sensor);
			Optional<User> user = userDao.findById(sensorAddData.getUserId());
			
			SensorLinker sensorLinker =new SensorLinker();
			sensorLinker.setSensor(savedSensor);
			sensorLinker.setUser(user.orElse(null));
			sensorDataLinkerDao.save(sensorLinker);
			
			return "data added successfully";
		}else {
			return "sensor  addition failed!!!";
		}
	
		
		
	}

}
