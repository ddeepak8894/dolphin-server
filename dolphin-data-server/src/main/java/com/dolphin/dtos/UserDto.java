package com.dolphin.dtos;

import java.util.ArrayList;
import java.util.List;

import com.dolphin.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data@AllArgsConstructor@NoArgsConstructor@Slf4j
public class UserDto {
	
	private int userId;
	
	
	private String firstName;
	
	private String lastName;
	
	private String email;

	private String password;
	
	private String role;
	
	private String cellNo;
	
	private String securityQuestion;
	
	private String securityAnswer;
	private String fullName;
	private Integer numberOfSensors;
	
    public static UserDto createDtoObject(User userToConvert) {
        log.debug("Converting User to UserDto: {}", userToConvert.getUserId());

        UserDto user = new UserDto(
            userToConvert.getUserId(),
            userToConvert.getFirstName(),
            userToConvert.getLastName(),
            userToConvert.getEmail(),
            userToConvert.getPassword(),
            userToConvert.getRole(),
            userToConvert.getCellNo(),
            userToConvert.getSecurityAnswer(),
            userToConvert.getSecurityQuestion(),
            userToConvert.getFirstName()+" "+
            userToConvert.getLastName(),
            userToConvert.getSensorLinkerList().size()
        );

        log.debug("User converted to UserDto: {}", user.getUserId());

        return user;
    }

	
    public static List<UserDto> createListOfAllUsers(List<User> userList) {
        log.debug("Creating a list of UserDto objects from User list...");

        List<UserDto> users = new ArrayList<>();
        
        userList.forEach((u) -> {
            UserDto userDto = UserDto.createDtoObject(u);
            users.add(userDto);
            log.info("Converted User {} to UserDto: {}", u.getUserId(), userDto.getUserId());
        });

        log.info("List of UserDto objects created successfully.");

        return users;
    }

	

}
