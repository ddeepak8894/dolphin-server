package com.dolphin.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString.Exclude;

@Data
@Entity(name = "sensor_linker")
public class SensorLinker {
	@Exclude@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)@Id
	private int sensorLinkerId;
	@Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="sensor_id")
	private Sensor sensor;
	
	@Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private User user;
}
