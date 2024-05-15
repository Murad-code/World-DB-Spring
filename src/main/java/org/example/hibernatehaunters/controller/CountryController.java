package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CountryEntity;
import org.example.hibernatehaunters.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

//    @GetMapping("/countries")
//    public List<CountryEntity> getAllCountries() {
//
//    }

//    @GetMapping("/country/{id}")
//    public CountryEntity getCountryById(@RequestParam Integer id) {
//
//    }

//    @PostMapping("/country")
//    public CountryEntity createCountry(@RequestBody CountryEntity country) {
//
//    }

    @PutMapping("/country")
    public CountryEntity updateCountry(@RequestBody CountryEntity country) {

    }
//
//    @DeleteMapping("/country/{id}")
//    public CountryEntity deleteCountryById(@RequestParam Integer id) {
//
//    }
}
