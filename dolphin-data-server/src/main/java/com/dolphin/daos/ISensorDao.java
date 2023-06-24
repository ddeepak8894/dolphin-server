package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolphin.entities.Sensor;

public interface ISensorDao extends JpaRepository<Sensor, Integer> {
	
	

}
