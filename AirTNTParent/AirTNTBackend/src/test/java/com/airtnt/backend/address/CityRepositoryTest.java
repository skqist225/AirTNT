package com.airtnt.backend.address;

import com.airtnt.common.entity.City;
import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityRepositoryTest {


    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testAddCity() {
        Country Japan = new Country(1);

        City city = City.builder()
                .name("Osaka")
                .country(Japan)
                .build();

        cityRepository.save(city);

    }

    @Test
    public void updateCityRepository() {
        City osaka = cityRepository.findById(1).get();
        State kansai = new State(1);

        osaka.setState(kansai);
        osaka.setCountry(null);

        cityRepository.save(osaka);
    }
}