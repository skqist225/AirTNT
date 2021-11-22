package com.airtnt.frontend.amentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.airtnt.common.entity.Amentity;
import com.airtnt.common.entity.AmentityCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmentityService {
    @Autowired
    AmentityRepository amentityRepository;

    public List<Amentity> getAmentities(String findCriteria) {
        List<Amentity> amentities = null;
        if (findCriteria == "prominent") {
            amentities = amentityRepository.findByProminent(true);
        } else if (findCriteria == "favorite") {
            amentities = amentityRepository.findByFavorite(true);
        } else {
            amentities = amentityRepository.findBySafe(true);
        }

        return amentities;
    }

    public List<Amentity> getFirst18Amentities() {
        List<Amentity> amentities = amentityRepository.getFirst18Amentities();

        return amentities;
    }

    public List<Amentity> getAllAmentities() {
        Iterator<Amentity> itr = amentityRepository.findAll().iterator();
        List<Amentity> amentities = new ArrayList<>();

        itr.forEachRemaining(amentities::add);

        return amentities;
    }
}
