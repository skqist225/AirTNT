package com.airtnt.frontend.room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.airtnt.common.entity.Category;
import com.airtnt.common.entity.Room;

@Service
public class RoomService {
	public static final int MAX_ROOM_PER_FETCH = 20;

	@Autowired
	private RoomRepository roomRepository;

	public Room save(Room room) {
		return roomRepository.save(room);
	}

	public Room getRoomById(int id) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		Room room = new Room();
		if (optionalRoom.isPresent()) {
			room = optionalRoom.get();
		}

		return room;
	}

	public List<Room> getRoomsByCategoryId(Category category, int page) {
		Pageable pageable = PageRequest.of(page - 1, MAX_ROOM_PER_FETCH);
		Iterator<Room> itr = roomRepository.findByCategory(category, pageable).iterator();
		List<Room> rooms = new ArrayList<>();

		itr.forEachRemaining(rooms::add);
		return rooms;
	}
}
