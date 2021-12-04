package com.airtnt.airtntapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan({ "com.airtnt.airtntapp" })
@EntityScan({ "com.airtnt.common.entity" })
@CrossOrigin(origins = { "http://localhost:8001" })
public class AirTntApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirTntApplication.class, args);
	}

}
