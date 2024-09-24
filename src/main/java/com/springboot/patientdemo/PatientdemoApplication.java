package com.springboot.patientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan("com.openapi.gen.springboot.api")
@SpringBootApplication(scanBasePackages = {"com.openapi.gen.springboot.api", "com.springboot.patientdemo"})
public class PatientdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientdemoApplication.class, args);
	}

}
