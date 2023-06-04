package com.dolphin.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity@Table(name = "data_table")
public class DataTable {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name_of_sensor;
	private int data;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_of_sensor() {
		return name_of_sensor;
	}
	public void setName_of_sensor(String name_of_sensor) {
		this.name_of_sensor = name_of_sensor;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public DataTable(int id, String name_of_sensor, int data) {
		super();
		this.id = id;
		this.name_of_sensor = name_of_sensor;
		this.data = data;
	}
	public DataTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
