package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolphin.entities.SensorLinker;

public interface ISensorLinkerDao extends JpaRepository<SensorLinker, Integer> {



}
