package com.dolphin.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dolphin.daos.IUserDao;
import com.dolphin.dtos.UserDto;
import com.dolphin.entities.User;

@Service@Transactional
public class UserService {
	
	@Autowired
	IUserDao userDao;
	
	public void addUser(User user) {
		
		userDao.save(user);
	}
	
	public UserDto findUserByCredentials(User user) {
		
		 user=userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
		 if(user != null) {
			 return new UserDto().createDtoObject(user);
		 }else {
			 return null;
		 }
		
		
	}
	
	public UserDto getUserById(int id) {
	    Optional<User> optionalUser = userDao.findById(id);

	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        return  UserDto.createDtoObject(user);
	       
	    } else {
	       return null;
	    }
	}
	
	

}
