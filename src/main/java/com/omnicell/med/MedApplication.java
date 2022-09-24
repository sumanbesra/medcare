package com.omnicell.med;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedApplication.class, args);
	}

}
