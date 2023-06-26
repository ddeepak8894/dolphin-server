package com.dolphin.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString.Exclude;

@Entity(name="sensor_data")@Data@Getter@Setter
public class SensorData {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)@Id
	@Column(name="id")
	private int sensorDataId;
	
	@Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="sensor_id")
	private Sensor sensor;
	
	private String data;
	
	 @Temporal(TemporalType.TIMESTAMP)@Column(name="last_updated_at")
	private Date lastUpdatedAt;
	
	
	

}
