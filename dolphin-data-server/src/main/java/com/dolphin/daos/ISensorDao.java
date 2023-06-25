package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolphin.entities.Sensor;
import java.util.List;
import java.util.Optional;


public interface ISensorDao extends JpaRepository<Sensor, Integer> {
	
	public Optional<Sensor> findByNameOfSensor(String nameOfSensor);

}
