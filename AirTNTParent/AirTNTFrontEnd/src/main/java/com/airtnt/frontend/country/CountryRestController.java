package com.airtnt.frontend.country;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CountryRestController {
    @GetMapping(value = "/countries")
    public String getCountries() {
        String url = "https://countriesnow.space/api/v0.1/countries/flag/unicode";
        RestTemplate restTemplate = new RestTemplate();
        String countries = restTemplate.getForObject(url, String.class);

        return countries;
    }

    @GetMapping(value = "/states/{countryName}")
    public String getStatesByCountry(@PathVariable("countryName") String countryName) {
        String url = "https://countriesnow.space/api/v0.1/countries/cities";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"country\":\"" + countryName + "\"}";
        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        String countries = restTemplate.postForObject(url, request, String.class);
        return countries;
    }
}
