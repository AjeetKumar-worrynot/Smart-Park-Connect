package com.example.parkingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.parkingsystem")
public class ParkingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingsystemApplication.class, args);
	}

}
