package com.airtnt.backend.room;

import java.util.List;
import java.util.NoSuchElementException;

import com.airtnt.common.Exception.RoomNotFoundException;
import com.airtnt.common.entity.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    public static final int ROOMS_PER_PAGE = 10;
    @Autowired RoomRepository repo;

    public Room save(Room room){
        return repo.save(room);
    }

    public Room getById(int id) throws RoomNotFoundException {
        try{
            return repo.getById(id);
        }catch(NoSuchElementException ex){
            throw new RoomNotFoundException("could not find room with id: " + id);
        }
    }

    public Page<Room> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
		
		sort = sortDir.equals("asc") ? sort.ascending() :sort.descending();
		
		Pageable pageable = PageRequest.of(pageNum -1, ROOMS_PER_PAGE, sort);
		
		if(keyword!=null) {
			return repo.findAll(keyword, pageable);
		}
		
		return repo.findAll(pageable);
    }

    public boolean isNameUnique(Integer id, String name) {
        Room room = repo.findByName(name);
		
		if(room == null) return true;
		
		boolean isCreatingNew = (id ==null);
		
		if(isCreatingNew) {
			if(room !=null) return false;
		} else {
			if(room.getId() != id) {
				return false;
			}
		}
		
		return true;
    }

    public void updateRoomEnabledStatus(Integer id, Boolean status){
        repo.updateStatus(id, status);
    }

    public void deleteRoom(Integer id) throws RoomNotFoundException{
        Long countById =  repo.countById(id);
		if((countById == null || countById == 0)) {
			throw new RoomNotFoundException("Could not find any room with ID " + id);
		}
		
		repo.deleteById(id);
    }
}
