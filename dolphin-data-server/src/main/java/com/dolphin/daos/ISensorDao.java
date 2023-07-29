package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dolphin.entities.Sensor;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface ISensorDao extends JpaRepository<Sensor, Integer> {
	
	public Optional<Sensor> findByNameOfSensor(String nameOfSensor);
	
	@Modifying
	@Query(value = "UPDATE sensor SET longitude = :longitude, latitude = :latitude WHERE name_of_sensor = :nameOfSensor", nativeQuery = true)
    public int updatePositionParameterOfSensor(@Param("nameOfSensor") String nameOfSensor, @Param("latitude") String latitude, @Param("longitude") String longitude);

	

}
