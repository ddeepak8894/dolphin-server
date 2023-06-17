package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolphin.entities.User;

public interface IUserDao extends JpaRepository<User, Integer> {

}
