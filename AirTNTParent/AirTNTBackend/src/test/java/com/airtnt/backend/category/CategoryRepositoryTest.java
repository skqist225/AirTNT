package com.airtnt.backend.category;

import com.airtnt.common.entity.Category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testAddCategory() {
//        Category usDollar = Category.builder().icon("ryokan.jpg").name("Ryokan").build();
        Category usDollar = Category.builder().icon("ryokajjjjn.jpg").name("Ryojjkan").build();

        categoryRepository.save(usDollar);
    }

    @Test
    public void testUpdateCurrency() {
        Category category = categoryRepository.findById(2).get();
        category.setName("tumlum");

        categoryRepository.save(category);
    }


}
