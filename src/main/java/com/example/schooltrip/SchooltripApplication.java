package com.example.schooltrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.CONTROLLER", "com.SERVICE", "com.MODEL"})
public class SchooltripApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchooltripApplication.class, args);
	}

}