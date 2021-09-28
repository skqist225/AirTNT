package com.airtnt.backend.promo;

import com.airtnt.common.entity.Promo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends CrudRepository<Promo, Integer> {
    public Promo findByPromoCode(String promoCode);
}
