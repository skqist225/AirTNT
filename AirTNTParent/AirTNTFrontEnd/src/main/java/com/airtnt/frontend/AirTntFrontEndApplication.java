package com.airtnt.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.airtnt.frontend"})
@EntityScan({"com.airtnt.common.entity"})
public class AirTntFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirTntFrontEndApplication.class, args);
	}

}
