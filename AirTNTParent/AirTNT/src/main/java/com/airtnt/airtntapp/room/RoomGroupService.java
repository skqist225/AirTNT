package com.airtnt.airtntapp.room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.airtnt.common.entity.RoomGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomGroupService {
    @Autowired
    RoomGroupRepository roomGroupRepository;

    public List<RoomGroup> getRoomGroups() {
        List<RoomGroup> roomGroups = new ArrayList<>();
        Iterator<RoomGroup> iterator = roomGroupRepository.findAll().iterator();

        while (iterator.hasNext()) {
            RoomGroup rt = (RoomGroup) iterator.next();
            roomGroups.add(rt);
        }

        return roomGroups;
    }
}
