package com.airtnt.backend.room;

import java.util.List;

import com.airtnt.common.entity.RoomType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomTypeReposutoryTest {
    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Test
    public void addRoomType() {
        RoomType rt = new RoomType("Căn hộ cho thuê",
                "Một địa điểm cho thuê trong một tòa nhà chung cư hoặc khu phức hợp.");

        roomTypeRepository.save(rt);
    }

    @Test
    public void addMultipleRoomType() {
        RoomType rt2 = new RoomType("Căn hộ (chung cư) cao cấp",
                "Một chỗ ở trong một tòa chung cư hoặc khu phức hợp thuộc sở hữu của cư dân.");
        RoomType rt3 = new RoomType("Tầng lửng",
                "Một căn hộ thường hoặc căn hộ chung cư cao cấp với thiết kế ưu tiên không gian thoáng, có thể có vách tường thấp bên trong.");
        RoomType rt4 = new RoomType("Căn hộ dịch vụ",
                "Một căn hộ có tiện nghi như khách sạn, do một công ty quản lý chuyên nghiệp cung cấp dịch vụ.");
        RoomType rt5 = new RoomType("Casa particular",
                "Một phòng riêng trong một ngôi nhà ở Cuba, cho cảm giác như chỗ nghỉ phục vụ bữa sáng.");
        RoomType rt6 = new RoomType("Nhà nghỉ dưỡng",
                "Một chỗ ở cho thuê đầy đủ tiện nghi, có nhà bếp và phòng tắm, có thể cung cấp một số dịch vụ cho khách, chẳng hạn như quầy lễ tân.");

        roomTypeRepository.saveAll(List.of(rt2, rt3, rt4, rt5, rt6));
    }
}
