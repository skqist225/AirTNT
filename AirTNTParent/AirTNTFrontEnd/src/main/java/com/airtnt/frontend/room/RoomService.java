package com.airtnt.frontend.room;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtnt.common.entity.Room;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public Room getRoomById(int id) {
		return roomRepository.findById(id).get();
	}
}
