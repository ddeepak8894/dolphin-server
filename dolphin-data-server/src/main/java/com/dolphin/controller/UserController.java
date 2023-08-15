package com.dolphin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolphin.dtos.Response;
import com.dolphin.dtos.SensorAddDto;
import com.dolphin.dtos.UserDto;
import com.dolphin.entities.User;
import com.dolphin.services.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user/")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        log.info("Received request to add user: {}", user);
        String responseMessage = userService.addUser(user);
        log.info("User addition response: {}", responseMessage);
        return Response.success(responseMessage);
    }
    
    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        log.info("Received request to update user: {}", user);
        String responseMessage = userService.updateUser(user);
        log.info("User addition response: {}", responseMessage);
        return Response.success(responseMessage);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        System.out.println(user.toString());
        if (userService.findUserByCredentials(user) != null) {
            return Response.success(userService.findUserByCredentials(user));
        } else {
            return Response.error("USER_DOES_NOT_EXIST");
        }
    }

    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody UserDto userDto) {
        log.info("Handling request to get user by ID: {}", userDto.getUserId());

        UserDto user = userService.getUserById(userDto.getUserId());

        if (user != null) {
            log.info("Returning a success response with the user: {}", user.getUserId());
            return Response.success(user);
        } else {
            log.info("Returning an error response: USER_DOES_NOT_EXIST");
            return Response.error("USER_DOES_NOT_EXIST");
        }
    }

    
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        System.out.println("**************************");
        log.info("Handling request to get all users...");

        List<UserDto> users = userService.getAllUsers();

        if (!users.isEmpty()) {
            log.info("Returning a success response with the list of users.");
            return Response.success(users);
        } else {
            log.info("Returning an error response: USER_DOES_NOT_EXIST");
            return Response.error("USER_DOES_NOT_EXIST");
        }
    }
}
