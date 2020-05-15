package com.cts.project.theater;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TheaterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheaterServiceApplication.class, args);
	}

}
