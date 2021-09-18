package com.airtnt.backend.address;

import com.airtnt.common.entity.City;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CityRepositoryTest {

	@Autowired
	private CityRepository cityRepository;

	@Test
	void testAddCity() {
		City osaka = City.builder().name("Osaka").build();

		cityRepository.save(osaka);

		assertThat(osaka.getName()).isEqualTo("Osaka");
		assertThat(osaka.getId()).isPositive();
	}

	@Test
	void updateCityRepository() {
		City osaka = cityRepository.findById(1).get();
		osaka.setName("kawasi");
		
		cityRepository.save(osaka);
		
		assertThat(osaka.getName()).isEqualTo("kawasi");
	}
}