package com.airtnt.airtntapp.room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.airtnt.common.entity.RoomType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeService {
    @Autowired
    RoomTypeRepository roomGroupRepository;

    public List<RoomType> getRoomTypes() {
        List<RoomType> roomTypes = new ArrayList<>();
        Iterator<RoomType> iterator = roomGroupRepository.findAll().iterator();

        while (iterator.hasNext()) {
            RoomType rt = (RoomType) iterator.next();
            roomTypes.add(rt);
        }

        return roomTypes;
    }
}
