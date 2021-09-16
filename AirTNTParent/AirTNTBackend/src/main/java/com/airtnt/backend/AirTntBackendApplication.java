package com.airtnt.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.airtnt.common.entity", "com.airtnt.backend"})
public class AirTntBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirTntBackendApplication.class, args);
	}

}
