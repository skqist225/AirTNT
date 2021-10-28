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
}
