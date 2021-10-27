package com.airtnt.backend.room;

import java.util.List;

import com.airtnt.common.entity.RoomGroup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomGroupRepositoryTest {
    @Autowired
    private RoomGroupRepository roomGroupRepository;

    @Test
    public void testAddRoomType() {
        RoomGroup rt = new RoomGroup("Căn hộ", "can_ho");

        roomGroupRepository.save(rt);
    }

    @Test
    public void testAddMultipleRoomType() {

        RoomGroup rt3 = new RoomGroup("Nhà", "nha");
        RoomGroup rt4 = new RoomGroup("Căn hộ phụ", "can_ho_phu");
        RoomGroup rt5 = new RoomGroup("Không gian độc đáo", "khong_gian_doc_dao");
        RoomGroup rt6 = new RoomGroup("Chỗ nghĩ phục vụ buổi sáng", "cho_nghi_phuc_vu_buoi_sang");
        RoomGroup rt7 = new RoomGroup("Khách sạn boutique", "khach_san_boutique");

        roomGroupRepository.saveAll(List.of(rt3, rt4, rt5, rt6, rt7));
    }

}
