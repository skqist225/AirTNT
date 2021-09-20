package com.airtnt.frontend.category;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.airtnt.common.entity.Category;
import com.airtnt.common.entity.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();

        Iterator<Category> itr = categoryRepository.findAll().iterator();
        itr.forEachRemaining(categories::add);

        return categories;
    }

    public Set<Room> getRoomsByCategoryId(int id) {
        Category category = categoryRepository.findById(id).get();
        Set<Room> rooms = category.getRoom();

        return rooms;
    }
}
