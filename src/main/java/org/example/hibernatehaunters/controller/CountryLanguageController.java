package org.example.hibernatehaunters.controller;


import org.example.hibernatehaunters.models.entities.CountryLanguageEntity;
import org.example.hibernatehaunters.service.CountryLanguageService;
import org.example.hibernatehaunters.service.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryLanguageController {
    private final CountryLanguageService countryLanguageService;
    private final WorldService worldService;

    @Autowired
    public CountryLanguageController(CountryLanguageService countryLanguageService, WorldService worldService) {
        this.countryLanguageService = countryLanguageService;
        this.worldService = worldService;
    }

    //read
    @GetMapping("/countryLanguages")
    public List<CountryLanguageEntity> getAllLanguages(){return countryLanguageService.getAllCountryLanguages();}


    @GetMapping("/countryLanguage")
    public List<CountryLanguageEntity> getCountryLanguageByCountry(@RequestParam String code) {
        return countryLanguageService.getCountryLanguageByCountryCode(code);
    }

    //create
    @PostMapping("/countryLanguage/create")
    public CountryLanguageEntity addLanguage(@RequestBody CountryLanguageEntity countryLanguage){
        countryLanguageService.createCountryLanguage(countryLanguage);
        return countryLanguage;
    }

    @PutMapping("/countryLanguage/update/official")
    public CountryLanguageEntity updateLanguageIsOfficial(@RequestBody CountryLanguageEntity countryLanguage){
        CountryLanguageEntity countryLanguageUpdated = countryLanguageService.getCountryLanguageById(countryLanguage.getId());
        countryLanguageService.updateCountryLanguage(countryLanguageUpdated.getId(), countryLanguage);
        return countryLanguageUpdated;
    }


    //delete
    @DeleteMapping("/countryLanguages/delete/country")
    public List<CountryLanguageEntity> deleteCountryLanguagesByCountryCode(@RequestParam String code)
    {
        List<CountryLanguageEntity> countryLanguageEntities = countryLanguageService.getCountryLanguageByCountryCode(code);
        for(CountryLanguageEntity ce : countryLanguageEntities)
        {
            countryLanguageService.deleteCountryLanguage(ce.getId());
        }
        return countryLanguageEntities;
    }


    @DeleteMapping("/countryLanguages/delete/language")
    public List<CountryLanguageEntity> deleteCountryLanguage (@PathVariable String name){
        List<CountryLanguageEntity> countryLanguageEntities = countryLanguageService.getCountryLanguageByLanguage(name);
        for(CountryLanguageEntity ce : countryLanguageEntities)
        {
            countryLanguageService.deleteCountryLanguage(ce.getId());
        }
        return countryLanguageEntities;
        
    }


}
