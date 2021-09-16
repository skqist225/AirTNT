package com.airtnt.backend.address;

import com.airtnt.common.entity.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryRepositoryTest {
    @Autowired
    private CountryRepository repository;

    @Test
    public void testAddCountry() {
        Country country = Country.builder().name("Japan").code("81").build();

        repository.save(country);
    }
}