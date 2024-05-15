package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CountryEntity;
import org.example.hibernatehaunters.service.CityService;
import org.example.hibernatehaunters.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    private final CountryService countryService;
    private final CityService cityService;

    @Autowired
    public CountryController(CountryService countryService, CityService cityService) {
        this.countryService = countryService;
        this.cityService = cityService;
    }


    @GetMapping("/countries")
    public List<CountryEntity> getAllCountries() {
        return countryService.getAllCountries();

    }

    @GetMapping("/country/{code}")
    public Optional<CountryEntity> getCountryById(@PathVariable String code) {
        return countryService.getCountryByCode(code);
    }

    @PostMapping("/country")
    public CountryEntity addCountry(@RequestBody CountryEntity country) {
        return countryService.createCountry(country);

    }

//    @PutMapping("/country")
//    public CountryEntity updateCountry(@RequestBody CountryEntity country) {
//
//    }
//
//    @DeleteMapping("/country/{id}")
//    public CountryEntity deleteCountryById(@RequestParam Integer id) {
//
//    }
}
