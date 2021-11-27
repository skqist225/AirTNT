package com.airtnt.backend.promo;

import java.util.List;

import com.airtnt.common.entity.Promo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromoRestController {
    @Autowired
    PromoRepository repo;

    @GetMapping("/promos/list")
    public List<Promo> listAll() {
        return (List<Promo>) repo.findAll();
    }

    @GetMapping("/promos/{id}")
    public Promo getById(@PathVariable("id") Integer id) {
        return repo.findById(id).get();
    }

    @PostMapping("/promos/save")
    public String save(@RequestBody Promo promo) {
        Promo savedPromo = repo.save(promo);
        return String.valueOf(savedPromo.getId());
    }

    @DeleteMapping("/promos/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
    }

    @PostMapping("/promos/check_promo_code")
    public String checkName(@Param("id") Integer id, @Param("promoCode") String promoCode) {
        Promo promo = repo.findByPromoCode(promoCode);

        if (promo == null)
            return "OK";

        if (id != null && promo.getId() == id)
            return "OK";

        return "Duplicated";
    }
}
