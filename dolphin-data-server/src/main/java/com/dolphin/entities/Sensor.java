package com.dolphin.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity@Data@Table(name="sensor")
public class Sensor {

	@GeneratedValue(strategy = GenerationType.IDENTITY)@Id
	private int sensorId;
	
	private String nameOfSensor;
	
	private String currentStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedAt;
	
	
	@OneToMany(mappedBy = "sensor",cascade = CascadeType.ALL )
	private List<SensorLinker> sensorLinkerList;
	
	@OneToMany(mappedBy = "sensor",cascade = CascadeType.ALL )
	private List<SensorData> sensorDataList;
	
	public void updateSensorLikerList(SensorLinker sensorLinker) {
		sensorLinker.setSensor(this);
		sensorLinkerList.add(sensorLinker);
	}
	
	public void sensorDataList(SensorData sensorData) {
		sensorData.setSensor(this);
		sensorDataList.add(sensorData);
	}

}
