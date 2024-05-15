package org.example.hibernatehaunters.controller;


import org.example.hibernatehaunters.models.entities.CountrylanguageEntity;
import org.example.hibernatehaunters.service.CountrylanguageService;
import org.example.hibernatehaunters.service.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CountrylanguageController {
    private final CountrylanguageService countrylanguageService;
    private final WorldService worldService;

    @Autowired
    public CountrylanguageController(CountrylanguageService countrylanguageService, WorldService worldService) {
        this.countrylanguageService = countrylanguageService;
        this.worldService = worldService;
    }

//    @GetMapping("/countryLanguages")
//    public List<CountrylanguageEntity> getAllLanguages(){return countrylanguageService.getAllCountryLanguages();}


//    @GetMapping("/countryLanguage/")
//    public Optional<CountrylanguageEntity> getCountryLanguageById(@RequestParam String language) {
//
//
//        return countrylanguageService;
//    }

    @GetMapping("/countryLanguages")
    public List<CountrylanguageEntity> getCountrylanguagesByCountry(@RequestParam String country) {
        return countrylanguageService.getAllCountryLanguages()
                .stream()
                .filter(countrylanguageEntity -> countrylanguageEntity.getCountryCode().toString().equals(worldService.findCountry(country).getCode()))
                .toList();
    }



}
