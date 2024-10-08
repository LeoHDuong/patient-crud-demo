package com.springboot.patientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.springboot"})
@SpringBootApplication
public class PatientdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientdemoApplication.class, args);
	}

}
