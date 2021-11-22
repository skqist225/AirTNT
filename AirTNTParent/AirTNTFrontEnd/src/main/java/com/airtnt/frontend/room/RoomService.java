package com.airtnt.frontend.room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.airtnt.common.entity.Category;
import com.airtnt.common.entity.Room;
import com.airtnt.common.entity.User;

@Service
@Transactional
public class RoomService {
	public static final int MAX_ROOM_PER_FETCH = 20;
	public static final int MAX_ROOM_PER_FETCH_BY_HOST = 3;

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

	public Page<Room> getRoomsByHost(User host, int pageNumber, Map<String, String> filters) {
		/*-------------------------------------------FILTER KEY------------------------------------------------*/
		int bedroomCount = Integer.parseInt(filters.get("bedroomCount"));
		int bathroomCount = Integer.parseInt(filters.get("bathroomCount"));
		int bedCount = Integer.parseInt(filters.get("bedCount"));
		String roomName = filters.get("query");
		String sortDir = filters.get("sortDir");
		String sortField = filters.get("sortField");

		/*-------------------------------------------FILTER KEY------------------------------------------------*/

		List<Integer> amentitiesID = new ArrayList<>();
		List<Boolean> statusesID = new ArrayList<>();

		if (!filters.get("amentities").isEmpty()) {
			String[] amentities = filters.get("amentities").split(" ");

			for (int i = 0; i < amentities.length; i++) {
				amentitiesID.add(Integer.parseInt(amentities[i]));
			}
		}

		if (!filters.get("status").isEmpty()) {
			String[] statuses = filters.get("status").split(" ");

			for (int i = 0; i < statuses.length; i++) {
				if (statuses[i].equals("ACTIVE")) {
					statusesID.add(true);
				}
				if (statuses[i].equals("UNLISTED")) {
					statusesID.add(false);
				}
			}
		}

		Sort sort = Sort.by(sortField);
		if (sortField.equals("location")) {
			Sort sortByCountry = Sort.by("country.name");
			Sort sortByState = Sort.by("state.name");
			Sort sortByCity = Sort.by("city.name");
			sort = sortByCountry.and(sortByState.and(sortByCity));
		}
		if (sortField.equals("lastModified")) {
			sort = Sort.by("updatedDate");
		}

		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, MAX_ROOM_PER_FETCH_BY_HOST, sort); // pase base 0

		/*-----------------------------OUPUT FILTER OPTION--------------------------------------------------- */
		for (Map.Entry<String, String> key : filters.entrySet()) {
			System.out.println("key: " + key.getKey() + " value: " + key.getValue());
		}
		/*-----------------------------OUPUT FILTER OPTION--------------------------------------------------- */

		if (amentitiesID.size() == 0) {
			return roomRepository.findAll(new Specification<Room>() {
				@Override
				public Predicate toPredicate(Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();

					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("host"), host)));
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + roomName + "%")));
					predicates.add(criteriaBuilder
							.and(criteriaBuilder.greaterThanOrEqualTo(root.get("bedroomCount"), bedroomCount)));
					predicates.add(criteriaBuilder
							.and(criteriaBuilder.greaterThanOrEqualTo(root.get("bathroomCount"), bathroomCount)));
					predicates.add(
							criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("bedCount"), bedCount)));
					Expression<Boolean> status = root.get("status");
					Predicate predicate = status.in(statusesID);
					predicates.add(predicate);

					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			}, pageable);
		}

		Page<Room> rooms = roomRepository.getRoomsByHost(host, roomName, bedroomCount, bathroomCount, bedCount,
				amentitiesID, statusesID, pageable);
		return rooms;
	}

	public List<Room> getRoomsByCategoryId(Category category, int page) {
		Pageable pageable = PageRequest.of(page - 1, MAX_ROOM_PER_FETCH);
		Iterator<Room> itr = roomRepository.findByCategory(category, pageable).iterator();
		List<Room> rooms = new ArrayList<>();

		itr.forEachRemaining(rooms::add);
		return rooms;
	}

	public int updateRoomStatus(Integer roomId) {
		return roomRepository.updateRoomStatus(roomId);
	}

	public int completeRentalProcess(Integer roomId) {
		return updateRoomStatus(roomId);
	}

	// public Page<Room> listByPage(int pageNum, String sortField, String sortDir,
	// String keyword) {
	// Sort sort = Sort.by(sortField);

	// sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
	// Pageable pageable = PageRequest.of(pageNum - 1, MAX_ROOM_PER_FETCH_BY_HOST,
	// sort);

	// if (keyword != null) {
	// return roomRepository.findAll(keyword, pageable);
	// }

	// return roomRepository.findAll(pageable);
	// }

	// public Page<Room> listByPage2(int pageNum, String sortField, String sortDir,
	// String[] keyword) {
	// Sort sort = Sort.by(sortField);

	// sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
	// Pageable pageable = PageRequest.of(pageNum - 1, MAX_ROOM_PER_FETCH_BY_HOST,
	// sort);

	// // for (String key : keyword) {

	// // }

	// // if (keyword != null) {
	// // return roomRepository.findAll(keyword, pageable);
	// // }

	// return roomRepository.findAll(pageable);
	// }

}
