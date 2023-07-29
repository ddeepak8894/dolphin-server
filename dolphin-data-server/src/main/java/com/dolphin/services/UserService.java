package com.dolphin.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import com.dolphin.daos.IUserDao;
import com.dolphin.dtos.UserDto;
import com.dolphin.entities.User;

import lombok.extern.slf4j.Slf4j;

@Service@Transactional@Slf4j
public class UserService {
	
	@Autowired
	IUserDao userDao;
	
    public String addUser(User user) {
        Optional<User> existingUser = userDao.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            userDao.save(user);
            log.info("User added successfully: {}", user.getEmail());
            return "USER_ADD_SUCCESS";
        } else {
            log.error("Failed to add user: Duplicate email address: {}", user.getEmail());
            return "USER_ADD_FAIL_DUPLICATE_EMAIL";
        }
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
