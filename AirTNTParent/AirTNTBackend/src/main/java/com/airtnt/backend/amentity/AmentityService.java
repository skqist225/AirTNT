package com.airtnt.backend.amentity;

import java.util.List;

import com.airtnt.common.entity.Amentity;
import com.airtnt.common.entity.AmentityCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmentityService {
    @Autowired AmentityRepository repo;

    public List<Amentity> listAll(){
        return (List<Amentity>) repo.findAll();
    }

    public Amentity getById(Integer id){
        return repo.findById(id).get();
    }

    public Amentity save(Amentity amentity){
        if(amentity.getId()!=null){
            Amentity amentityDB = repo.findById(amentity.getId()).get();
            if(amentity.getIconImage()==null) amentity.setIconImage(amentityDB.getIconImage());

            return repo.save(amentity);
        }
        return repo.save(amentity);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public String checkName(Integer id, String name) {
        Amentity amentity = repo.findByName(name);

        if(amentity==null) return "OK";

        if(id!=null && amentity.getId()!=id) return "OK";

        return "Duplicated";
    }
}
