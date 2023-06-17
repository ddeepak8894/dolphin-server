package com.dolphin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dolphin.daos.IUserDao;
import com.dolphin.entities.User;

@Service@Transactional
public class UserService {
	
	@Autowired
	IUserDao userDao;
	
	public void addUser(User user) {
		
		userDao.save(user);
	}
	
	

}
