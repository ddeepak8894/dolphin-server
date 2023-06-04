package com.dolphin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DolphinDataServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DolphinDataServerApplication.class, args);
		System.out.println("					=================================================	");
		System.out.println("					==========Welcome to Sensor Data Store=========		");
		System.out.println("					=================================================	");
	}

}
