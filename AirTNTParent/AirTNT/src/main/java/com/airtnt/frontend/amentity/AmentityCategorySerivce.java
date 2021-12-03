package com.airtnt.frontend.amentity;

import java.util.List;

import com.airtnt.common.entity.AmentityCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmentityCategorySerivce {
    @Autowired
    AmentityCategoryRepository repo;

    public List<AmentityCategory> listAll() {
        return (List<AmentityCategory>) repo.findAll();
    }
}
