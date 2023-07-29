package com.dolphin.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

	@GeneratedValue(strategy = GenerationType.IDENTITY)@Id@Column(name="sensor_id")
	private int sensorId;
	@Column(name="name_of_sensor")
	private String nameOfSensor;
	
	@Column(name="current_status")
	private String currentStatus;
	
	@Column(name="longitude")
	private String longitude;
	
	@Column(name="latitude")
	private String latitude;
	
	@Column(name="last_updated_at")
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
