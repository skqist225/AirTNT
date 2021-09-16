package com.airtnt.backend.address;

import com.airtnt.common.entity.City;
import com.airtnt.common.entity.Country;
import com.airtnt.common.entity.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StateRepositoryTest {

    @Autowired
    private StateRepository stateRepository;

    @Test
    public void addTestState() {
        Country Japan = new Country(1);

        State kansai = State.builder()
                .name("Kansai")
                .country(Japan)
                .build();

        stateRepository.save(kansai);
    }
}