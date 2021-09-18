package com.airtnt.backend.address;

import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

@SpringBootTest
class CountryRepositoryTest {
    @Autowired
    private CountryRepository repository;

    @Test
    public void testAddCountry() {
        Country country = Country.builder().name("Japan").code("81").build();

        repository.save(country);
        
        assertThat(country.getName()).isEqualTo("Japan");
        assertThat(country.getCode()).isEqualTo("81");
        assertThat(country.getId()).isGreaterThan(0);
    }

    @Test
    public void testAddStatesAndCities() {
        State state1 = State.builder().name("nagawa").build();
        State state2 = State.builder().name("kosaki").build();
        State state3 = State.builder().name("mitsubisi").build();

        Country country =  repository.findById(1).get();
        country.getStates().addAll(Set.of(state1, state2,state3));

        repository.save(country);
    }
}