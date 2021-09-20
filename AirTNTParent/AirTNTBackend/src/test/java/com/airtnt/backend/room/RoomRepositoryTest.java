package com.airtnt.backend.room;

import com.airtnt.backend.room.RoomRepository;
import com.airtnt.backend.rule.RuleRepository;
import com.airtnt.common.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootTest
class RoomRepositoryTest {

	@Autowired
	private RoomRepository repository;

	@Autowired
	private RuleRepository ruleRepository;

	@Test
	public void testAddRoom() {
		String description = "GOSHOBO is the oldest ryokan hotel in Arima Onsen, one of the three historical hot springs in Japan. It has served people since its foundation in the 12th century.\n"
				+ "\n"
				+ "The guest room is a nostalgic tatami room with two beds, with a modern updated toilet attached. You can view a serene Zen garden and the refreshing Mt. Rokko.\n"
				+ "\n" + "The public hot spring bath in the hotel is open from 3 pm to 9:30 am.";

		Amentity amentity1 = new Amentity(1);
		Amentity amentity3 = new Amentity(3);
		Amentity amentity4 = new Amentity(4);
		Amentity amentity5 = new Amentity(5);
		Amentity amentity6 = new Amentity(6);
		Amentity amentity7 = new Amentity(7);

		Set<Amentity> amentities = new HashSet<>();
		amentities.addAll(Set.of(amentity1, amentity3, amentity4, amentity5, amentity6, amentity7));
		Set<Image> images = new HashSet<>();

		for (int i = 0; i <= 15; i++) {
			Image image = Image.builder().image(String.valueOf(i)).build();
			images.add(image);
		}

		City city = new City(1);
		State state = new State(1);
		Country country = new Country(1);

		Set<Rule> rules = new HashSet<>();
		Iterator<Rule> itr = ruleRepository.findAll().iterator();
		while (itr.hasNext()) {
			rules.add(itr.next());
		}

		User host = new User(2);

		Room room = Room.builder().name(
				"A Relaxing Stay at a Historical Hot Spring Ryokan Hotel in Arima(a twin room, up to two people)歴史的木造建築の旅館,景観は庭のツイン【2名定員】")
				.accomodatesCount("2").bathroomCount("1").bedCount("2").bedroomCount("1").description(description)
				.amentities(amentities).images(images).price(364).priceType(PriceType.PER_NIGHT).mininumStay(1)
				.stayType(StayType.DAY).city(city).state(state).country(country).rules(rules).host(host).build();

		repository.save(room);
	}

	@Test
	public void deleteRoomById() {
		repository.deleteById(7);
	}

	@Test
	public void getRoomById() {
		Room room = repository.findById(8).get();

		System.out.println(room);
	}

}