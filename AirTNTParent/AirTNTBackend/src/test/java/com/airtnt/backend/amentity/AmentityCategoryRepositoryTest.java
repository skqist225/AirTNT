package com.airtnt.backend.amentity;

import com.airtnt.common.entity.AmentityCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class AmentityCategoryRepositoryTest {

	@Autowired
	private AmentityCategoryRepository repository;

	@Test
	void testAddAmentityCategory() {
		AmentityCategory amentityCategory = AmentityCategory.builder().name("Phòng tắm").build();

		repository.save(amentityCategory);

		assertThat(amentityCategory.getName()).isEqualTo("Phòng tắm");
		assertThat(amentityCategory.getId()).isPositive();
	}

	@Test
	void testAddListAmentityCategory() {
		AmentityCategory amentityCategory1 = AmentityCategory.builder().name("Dịch vụ").build();
		AmentityCategory amentityCategory2 = AmentityCategory.builder().name("Phòng ngủ và giặt ủi").build();
		AmentityCategory amentityCategory3 = AmentityCategory.builder().name("Giải trí").build();
		AmentityCategory amentityCategory4 = AmentityCategory.builder().name("Hệ thống sưởi và làm mát").build();
		AmentityCategory amentityCategory5 = AmentityCategory.builder().name("Internet và văn phòng").build();

		Iterator<AmentityCategory> amentityCategories = repository.saveAll(
				List.of(amentityCategory1, amentityCategory2, amentityCategory3, amentityCategory4, amentityCategory5))
				.iterator();
		List<AmentityCategory> result = new ArrayList<>();
		amentityCategories.forEachRemaining(result::add);

		assertThat(result.size()).isEqualTo(5);
	}

	@Test
	void testDeleteOneAmentityCategory() {
		AmentityCategory testAmentity = AmentityCategory.builder().name("Phòng bơiiii và ngủ giặt").build();

		repository.save(testAmentity);

		assertThat(testAmentity.getName()).isEqualTo("Phòng bơiiii và ngủ giặt");
		assertThat(testAmentity.getId()).isPositive();

		repository.delete(testAmentity);
	}
	


	@Test
	void updateOneAmentityCategory() {
		AmentityCategory amentityCategory = repository.findById(1).get();
		amentityCategory.setName("Phòng tắm");
		repository.save(amentityCategory);

		assertThat(amentityCategory.getName()).isEqualTo("Phòng tắm");
	}
}