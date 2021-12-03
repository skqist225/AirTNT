package com.airtnt.frontend.country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.airtnt.common.entity.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CountryRestController {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService service;

    @GetMapping(value = "/countries")
    public String getCountries() {
        String url = "https://countriesnow.space/api/v0.1/countries/iso";
        RestTemplate restTemplate = new RestTemplate();
        String countries = restTemplate.getForObject(url, String.class);

        return countries;

    }

    @PostMapping(value = "/countries")
    public String addCountriesToDB(@RequestBody Map<String, List<CountryDTO>> payload) {
        List<CountryDTO> countries = payload.get("countries");
        List<Country> lst_country = new ArrayList<>();

        for (CountryDTO c : countries) {
            Country country = new Country(c.getName(), c.getCode());
            lst_country.add(country);
        }

        countryRepository.saveAll(lst_country);
        return "OK";
    }

    @GetMapping(value = "/states/{countryName}")
    public String getStatesByCountry(@PathVariable("countryName") String countryName) {
        if (countryName.equals("vietnam")) {
            String url2 = "https://provinces.open-api.vn/api/?depth=2";
            RestTemplate restTemplate2 = new RestTemplate();
            String countries2 = restTemplate2.getForObject(url2, String.class);

            return countries2;
        }

        String url = "https://countriesnow.space/api/v0.1/countries/cities";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"country\":\"" + countryName + "\"}";
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        String countries = restTemplate.postForObject(url, request, String.class);

        return countries;
    }

    @GetMapping("/countries/list")
    public List<Country> listAll() {
        return service.listAll();
    }

    @GetMapping("/countries/{id}")
    public Country getById(
            @PathVariable("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping("/countries/save")
    public String save(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam("name") String name,
            @RequestParam("code") String code) {
        Country country;
        if (id != null)
            country = new Country(id, name, code);
        else
            country = new Country(name, code);
        Country saved = service.save(country);
        return String.valueOf(saved.getId());
    }

    @DeleteMapping("/countries/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

    @PostMapping("/countries/check_name")
    public String checkName(@Param("id") Integer id,
            @Param("name") String name) {
        return service.checkName(id, name);
    }
}
