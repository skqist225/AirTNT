package com.airtnt.backend.amentity;

import com.airtnt.common.entity.AmentityCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AmentityCategoryRepositoryTest {

    @Autowired
    private AmentityCategoryRepository repository;

    @Test
    public void testAddAmentityCategory() {
        AmentityCategory amentityCategory = AmentityCategory.builder().name("Phòng tắm").build();

        repository.save(amentityCategory);
    }

    @Test
    public void testAddMultipleAmentityCategory() {
        AmentityCategory amentityCategory1 = AmentityCategory.builder().name("Dịch vụ").build();
        AmentityCategory amentityCategory2 = AmentityCategory.builder().name("Phòng ngủ và giặt ủi").build();
        AmentityCategory amentityCategory3 = AmentityCategory.builder().name("Giải trí").build();
        AmentityCategory amentityCategory4 = AmentityCategory.builder().name("Hệ thống sưởi và làm mát").build();
        AmentityCategory amentityCategory5 = AmentityCategory.builder().name("Internet và văn phòng").build();

        repository.saveAll(List.of(amentityCategory1, amentityCategory2, amentityCategory3,amentityCategory4, amentityCategory5));
    }

    @Test
    public void testDeleteOneAmentityCategory() {
        //if amentity_category_id is not exists in amentity_table

        repository.deleteById(3);
    }

    @Test
    public void updateOneAmentityCategory() {
        AmentityCategory amentityCategory = repository.findById(1).get();
        amentityCategory.setName("Phòng tắm");
        repository.save(amentityCategory);
    }
}