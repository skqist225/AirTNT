package com.airtnt.backend.address;

import com.airtnt.common.entity.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StateRepositoryTest {

	@Autowired
	private StateRepository stateRepository;

	@Test
	public void addTestState() {
		State kansai = State.builder().name("Kansai").build();
		stateRepository.save(kansai);
	}

	@Test
	public void testFetchCountry() {
		State state = stateRepository.findById(1).get();

		System.out.println(state);
	}
}