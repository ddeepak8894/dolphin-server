package com.dolphin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.dtos.Response;
import com.dolphin.dtos.UserDto;
import com.dolphin.entities.User;
import com.dolphin.services.UserService;

@CrossOrigin("*")
@RestController @RequestMapping("/api/user/")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		System.out.println(user.toString());
		userService.addUser(user);
		return Response.success("USER_ADDED_SUCCESSFULLY");
		
	}
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody User user) {
		System.out.println(user.toString());
			if(userService.findUserByCredentials(user)!=null) {
				return Response.success(userService.findUserByCredentials(user));
			}else
			{
				return Response.error("USER_DOES_NOT_EXIST");
			}
		
		
		
	}
	
	@PostMapping("/getUser")
	public ResponseEntity<?> getUser(@RequestBody UserDto user) {
		
		user= userService.getUserById(user.getUserId());
		
			if(user!=null) {
				return Response.success(user);
			}else
			{
				return Response.error("USER_DOES_NOT_EXIST");
			}
		
		
		
	}

}
