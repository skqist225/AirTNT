package com.airtnt.frontend.amentity;

import java.util.List;

import com.airtnt.common.entity.Amentity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmentityRepository extends CrudRepository<Amentity, Integer> {
    public List<Amentity> findByProminent(boolean isProminent);

    public List<Amentity> findByFavorite(boolean isFavorite);

    public List<Amentity> findBySafe(boolean isSafe);
}
