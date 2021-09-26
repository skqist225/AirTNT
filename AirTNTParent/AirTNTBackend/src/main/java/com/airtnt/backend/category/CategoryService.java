package com.airtnt.backend.category;

import java.util.List;

import com.airtnt.common.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired CategoryRepository repo;
    public List<Category> listAll(){
        return (List<Category>)repo.findAll();
    } 
    public Category findById(Integer id){
        return repo.findById(id).get();
    }

    public Category save(Category category){
        if(category.getId()!=null){
            System.out.println("tao day ne");
            Category categoryDB = repo.findById(category.getId()).get();
            categoryDB.setName(category.getName());
            categoryDB.setStatus(category.isStatus());
            if(category.getIcon()!=null)categoryDB.setIcon(category.getIcon());
            return repo.save(categoryDB);
        }
        return repo.save(category);
    }
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
