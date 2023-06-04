package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolphin.entities.DataTable;

public interface IDataTableDao extends JpaRepository<DataTable, Integer> {

}
