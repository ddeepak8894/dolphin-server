package com.dolphin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
@IntegrationComponentScan
@SpringBootApplication@Slf4j@ComponentScan(basePackages = { "mqtt_handlers","config","com.dolphin"})@EnableScheduling
public class DolphinDataServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DolphinDataServerApplication.class, args);
		log.error("hare krushna !!!!!!!!!!!");
		System.out.println("					=================================================	");
		System.out.println("					==========Welcome to Sensor Data Store=========		");
		System.out.println("					=================================================	");
	}

}
