package com.dolphin.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
		
		String userEmail = data.getNameOfSensor().split("-")[0];
		Optional<User> user= userDao.findByEmail(userEmail);
		Optional<Sensor>sensor=sensorDao.findByNameOfSensor(data.getNameOfSensor());
		
		if(user.isPresent() && sensor.isPresent() ) {
			System.out.println("sensor present or not=== ");
			SensorData sensorData = new SensorData();
			sensorData.setData(data.getData().toString());
			sensorData.setSensor(sensor.get());
			sensorData.setLastUpdatedAt(new Date());
			sensorDataDao.save(sensorData);
			return "data added successfully";
		}else {
			return "failed !!!!";
		}



	}
	
	
	public String addSensor(SensorAddDto sensorAddData) {
		
		if(userDao.findById(sensorAddData.getUserId()).isPresent()) {
			Optional<User> user = userDao.findById(sensorAddData.getUserId());
			Sensor sensor = new Sensor();
			sensor.setNameOfSensor(user.get().getEmail()+"-"+sensorAddData.getNameOfSensor());
			sensor.setCurrentStatus(sensorAddData.getCurrentStatus());
			sensor.setLastUpdatedAt(new Date());
			Sensor savedSensor = sensorDao.save(sensor);
			SensorLinker sensorLinker =new SensorLinker();
			sensorLinker.setSensor(savedSensor);
			sensorLinker.setUser(user.orElse(null));
			sensorDataLinkerDao.save(sensorLinker);
			
			return "SENSOR_ADDED_SUCCESS";
		}else {
			return "SENSOR_ADDITION_FAILED";
		}
	
		
		
	}
	public List<SensorAddDto> getAllSensorsOfUser(int id){
		System.out.println("inside getAllSensorsOfUser =======");
		Optional<User> userOptional=userDao.findById(id);
		User user = userOptional.orElse(null);
		System.out.println("inside getAllSensorsOfUser ======= usernake"+user.toString());
		List<SensorLinker> linkerList =user.getSensorLinkerList();
        if (user != null) {
        	System.out.println("befor for each in getAllSensorsOfUser");
        	linkerList.forEach((e)->{System.out.println(e.toString());});
          return SensorAddDto.getListFromSensorLinkerList(linkerList);
        	
        } 
		
		return null;
	}

}
