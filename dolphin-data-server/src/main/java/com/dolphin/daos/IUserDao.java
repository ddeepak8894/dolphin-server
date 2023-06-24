package com.dolphin.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dolphin.entities.User;
import java.util.List;


public interface IUserDao extends JpaRepository<User, Integer> {
	
	User findByEmailAndPassword(String email, String password);

}
