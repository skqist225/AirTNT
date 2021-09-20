package com.airtnt.backend.category;

import com.airtnt.common.entity.Category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testAddCategory() {
        List<String> categoryName = new ArrayList<>();
        List<String> categoryIcon = new ArrayList<>();
        String extension = ".jpg";

        categoryName.add("Nhà trên cây");
        categoryName.add("Hồ bơi tuyệt vời");
        categoryName.add("Kezhan");
        categoryName.add("Nhà container");
        categoryName.add("Hướng biển");
        categoryName.add("Ryokan");
        categoryName.add("Nhà nhỏ");
        categoryName.add("Đảo");
        categoryName.add("Cabin");
        categoryName.add("Nhà mái vòm");
        categoryName.add("Trang trại");
        categoryName.add("Lâu đài");
//        categoryName.add("Lều yurt");
//        categoryName.add("Xe cắm trại");
//        categoryName.add("Nhà thuyền");
//        categoryName.add("Thuyền");
//        categoryName.add("Nhà khung chữ A");
//        categoryName.add("Nhà nông trại");
//        categoryName.add("Nhà phong cách Cycladic");
//        categoryName.add("Nhà chỏm nón");

        categoryIcon.add("ntc");
        categoryIcon.add("hbtv");
        categoryIcon.add("kz");
        categoryIcon.add("nc");
        categoryIcon.add("hb");
        categoryIcon.add("rk");
        categoryIcon.add("nn");
        categoryIcon.add("d");
        categoryIcon.add("cb");
        categoryIcon.add("nmv");
        categoryIcon.add("tt");
        categoryIcon.add("ld");

        if(categoryIcon.size() == categoryName.size()) {
            for (int i = 0; i < categoryIcon.size(); i++) {
                    categoryIcon.set(i, categoryIcon.get(i) + extension);

                Category category = Category.builder()
                        .name(categoryName.get(i))
                        .icon(categoryIcon.get(i))
                        .build();

                categoryRepository.save(category);
            }
        }



//        categoryRepository.save(usDollar);
    }

    @Test
    void testAddListCategory() {

    }

}
