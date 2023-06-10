package com.dolphin.daos;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dolphin.entities.DataTable;

public interface IDataTableDao extends JpaRepository<DataTable, Integer> {

//	@Modifying
//	@Query(value="insert into data_table values (:id,:name_of_sensor, :data)",nativeQuery = true)
//	int insertIntoSensorDataTable(@Param("id") int id,@Param("name_of_sensor") String name_of_sensor,@Param("data")Integer data);
	@Modifying
	@Query(value = "INSERT INTO data_table (id, name_of_sensor, data, sample_taken_at) VALUES (:id, :name_of_sensor, :data, :sample_taken_at)", nativeQuery = true)
	int insertIntoSensorDataTable(@Param("id") int id, @Param("name_of_sensor") String name_of_sensor, @Param("data") Integer data, @Param("sample_taken_at") Date sample_taken_at);

	@Modifying
	@Query(value = "DELETE FROM data_table WHERE sample_taken_at < DATE_SUB(NOW(), INTERVAL 20 MINUTE)", nativeQuery = true)
	int deleteOldSamples();


}
