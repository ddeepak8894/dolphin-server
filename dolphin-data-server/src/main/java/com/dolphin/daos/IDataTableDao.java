package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dolphin.entities.DataTable;

public interface IDataTableDao extends JpaRepository<DataTable, Integer> {

	@Modifying
	@Query(value="insert into data_table values (:id,:name_of_sensor, :data)",nativeQuery = true)
	int insertIntoSensorDataTable(@Param("id") int id,@Param("name_of_sensor") String name_of_sensor,@Param("data")Integer data);



}
