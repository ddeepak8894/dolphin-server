package com.dolphin.services;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dolphin.DolphinDataServerApplication;
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
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional@Slf4j
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
		Optional<User> user = userDao.findByEmail(userEmail);
		Optional<Sensor> sensor = sensorDao.findByNameOfSensor(data.getNameOfSensor());

		if (user.isPresent() && sensor.isPresent()) {
			System.out.println("sensor present or not=== ");
			SensorData sensorData = new SensorData();
			sensorData.setData(data.getData().toString());
			sensorData.setSensor(sensor.get());
			sensorData.setLastUpdatedAt(new Date());
			sensorDataDao.save(sensorData);
			return "data added successfully";
		} else {
			return "failed !!!!";
		}
	}
	
	@Scheduled(cron = "0 */10 * * * *")
	public void deleteOldSensorData() {
	    // Calculate the date 10 minutes ago
	    Date tenMinutesAgo = new Date(System.currentTimeMillis() - (10 * 60 * 1000));

	    // Delete sensorData entries older than tenMinutesAgo
//	    sensorDataDao.deleteByLastUpdatedAtBefore(tenMinutesAgo);
	}
	

	
	public String updatePositionParameterOfSensor(SensorAddDto sensorData) {
	    if (sensorDao.findByNameOfSensor(sensorData.getNameOfSensor()).isPresent()) {
	        try {
	            int rowChanged = sensorDao.updatePositionParameterOfSensor(
	                sensorData.getNameOfSensor(),
	                sensorData.getLatitude(),
	                sensorData.getLongitude()
	            );

	            if (rowChanged > 0) {
	                log.info("Sensor position updated successfully. Rows affected: {}", rowChanged);
	                return "UPDATE_SUCCESS";
	            } else {
	                log.warn("No rows were updated for sensor: {}", sensorData.getNameOfSensor());
	                return "UPDATE_FAILED_NO_ROWS_UPDATED";
	            }
	        } catch (Exception e) {
	            log.error("Error occurred while updating sensor position: {}", e.getMessage());
	            return "UPDATE_ERROR";
	        }
	    } else {
	        log.warn("Sensor with name '{}' not found in the database.", sensorData.getNameOfSensor());
	        return "SENSOR_NOT_FOUND";
	    }
	}

	public String addSensor(SensorAddDto sensorAddData) {
		log.info("adding sensor : "+sensorAddData.getNameOfSensor());
		if (userDao.findById(sensorAddData.getUserId()).isPresent()) {
			Optional<User> user = userDao.findById(sensorAddData.getUserId());
			Sensor sensor = new Sensor();
			sensor.setNameOfSensor(user.get().getEmail() + "-" + sensorAddData.getNameOfSensor());
			sensor.setCurrentStatus(sensorAddData.getCurrentStatus());
			sensor.setLastUpdatedAt(new Date());
			Sensor savedSensor = sensorDao.save(sensor);
			SensorLinker sensorLinker = new SensorLinker();
			sensorLinker.setSensor(savedSensor);
			sensorLinker.setUser(user.orElse(null));
			sensorDataLinkerDao.save(sensorLinker);

			return "SENSOR_ADDED_SUCCESS";
		} else {
			return "SENSOR_ADDITION_FAILED";
		}

	}
	   public List<SensorAddDto> getAllSensorsOfUser(int id) {
	        log.info("Received request to get all sensors of user with ID: {}", id);

	        Optional<User> userOptional = userDao.findById(id);
	        User user = userOptional.orElse(null);

	        if (user != null) {
	            log.info("User found: {}", user);
	            List<SensorLinker> linkerList = user.getSensorLinkerList();
	            log.info("Number of SensorLinker items for user with ID {}: {}", id, linkerList.size());

	            linkerList.forEach((linker) -> {
	                log.info("SensorLinker item: {}", linker);
	            });

	            List<SensorAddDto> sensorList = SensorAddDto.getListFromSensorLinkerList(linkerList);
	            log.info("Returning {} sensor(s) for user with ID: {}", sensorList.size(), id);
	            return sensorList;
	        } else {
	            log.warn("User not found for ID: {}", id);
	            return null;
	        }
	    }
	   
	public List<SensorDataAddDto> getDataOfSensorByID(int id) {
		if(sensorDao.findById(id).isPresent()) {
			List<SensorData> list=sensorDataDao.findBySensor_SensorId(id);
			return SensorDataAddDto.createDataList(list);
		}else {
			return null;
		}
	
		
	}
	
	

}
