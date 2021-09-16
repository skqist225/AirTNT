//package com.airtnt.backend;
//
//import com.airtnt.common.entity.Amentity;
//import com.airtnt.common.entity.Room;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@SpringBootTest
//class RoomRepositoryTest {
//
//	@Autowired
//	private RoomRepository repository;
//
//	@Test
//	public void testAddRoom() {
//
//		String description = "GOSHOBO is the oldest ryokan hotel in Arima Onsen, one of the three historical hot springs in Japan. It has served people since its foundation in the 12th century.\n"
//				+ "\n"
//				+ "The guest room is a nostalgic tatami room with two beds, with a modern updated toilet attached. You can view a serene Zen garden and the refreshing Mt. Rokko.\n"
//				+ "\n" + "The public hot spring bath in the hotel is open from 3 pm to 9:30 am.";
//
//		Amentity amentity1 =Amentity.builder().id(1).build();
//
//		Set<Amentity> amentities = new HashSet<>();
//		amentities.add(amentity1);
//
//		Room room = Room.builder().name(
//				"A Relaxing Stay at a Historical Hot Spring Ryokan Hotel in Arima(a twin room, up to two people)歴史的木造建築の旅館,景観は庭のツイン【2名定員】")
//				.accomodatesCount("2").bathRoomCount("1").bedCount("2").bedRoomCount("1").description(description)
//				.amentities(amentities).build();
//
//		repository.save(room);
//	}
//}