package com.dolphin.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolphin.daos.IDataTableDao;
import com.dolphin.entities.DataTable;

import jakarta.transaction.Transactional;

@Service@Transactional
public class DataTableServices {
	@Autowired
	IDataTableDao dataTableDao;
	
	public List<DataTable> getAllData(){
		List<DataTable> datas=dataTableDao.findAll();
		return datas;
			
	}
	
	public void addData(DataTable data) {
		dataTableDao.insertIntoSensorDataTable(0, data.getName_of_sensor(), data.getData(),new Date());
	}
	
	public void deleteAllData() {
		dataTableDao.deleteAll();
	}

}
