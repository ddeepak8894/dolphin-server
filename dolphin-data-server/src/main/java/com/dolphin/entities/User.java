package com.dolphin.entities;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity@Table(name="users")@Getter@Setter@NoArgsConstructor@ToString@JsonInclude(value = Include.NON_NULL)@DynamicInsert@DynamicUpdate
public class User {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name="user_id")
	private int userId;
	
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
    @Column(name = "email", unique = true) // Adding the unique constraint to the email field
    private String email;
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;
	@Column(name="cell_no")
	private String cellNo;
	
	@Column(name="name_of_society")
	private String nameOfSociety;
	
	@Column(name="security_question")
	private String securityQuestion;
	@Column(name="security_answer")
	private String securityAnswer;
	
	@Exclude
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL )
	private List<SensorLinker> sensorLinkerList;
	
	public void updateSensorLikerList(SensorLinker sensorLinker) {
		sensorLinker.setUser(this);
		sensorLinkerList.add(sensorLinker);
	}
	
	
	
	

}
