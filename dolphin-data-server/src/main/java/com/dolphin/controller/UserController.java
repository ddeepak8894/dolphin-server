package com.dolphin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.dtos.Response;
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
		return Response.error("user added success");
		
	}

}
