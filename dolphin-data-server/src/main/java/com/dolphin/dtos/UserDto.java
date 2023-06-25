package com.dolphin.dtos;





import com.dolphin.entities.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
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
	
	public static UserDto createDtoObject(User userToConvert) {
		
		UserDto user = new UserDto(userToConvert.getUserId(),userToConvert.getFirstName(),userToConvert.getLastName(),userToConvert.getEmail()
				,userToConvert.getPassword(),userToConvert.getRole(),userToConvert.getCellNo(),userToConvert.getSecurityAnswer(),userToConvert.getSecurityQuestion());
		return user;
	}

	

}
