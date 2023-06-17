package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolphin.entities.SensorData;

public interface ISensorData extends JpaRepository<SensorData, Integer> {

}
