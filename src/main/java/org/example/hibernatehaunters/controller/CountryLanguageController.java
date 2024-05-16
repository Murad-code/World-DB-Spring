package org.example.hibernatehaunters.controller;


import org.apache.coyote.BadRequestException;
import org.example.hibernatehaunters.models.entities.CountryLanguageEntity;
import org.example.hibernatehaunters.models.exceptions.countrylanguage.CountryLanguageBadRequestException;
import org.example.hibernatehaunters.models.exceptions.countrylanguage.CountryLanguageNotFoundException;
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
    public List<CountryLanguageEntity> getCountryLanguageByCountry(@RequestParam String code) throws CountryLanguageNotFoundException {
        List<CountryLanguageEntity> countryLanguageEntities = countryLanguageService.getCountryLanguageByCountryCode(code);
        if (countryLanguageEntities.isEmpty()){
            throw new CountryLanguageNotFoundException("With code: " + code);
        }
        return countryLanguageService.getCountryLanguageByCountryCode(code);
    }


    //create
    @PostMapping("/countryLanguage/create")
    public CountryLanguageEntity addLanguage(@RequestBody CountryLanguageEntity countryLanguage) throws CountryLanguageBadRequestException {

        try {
            countryLanguageService.createCountryLanguage(countryLanguage);
        } catch(Exception e){
            throw new CountryLanguageBadRequestException("Country language creation failed: " + e.getMessage());
        }
        return countryLanguage;
    }
    //update
    @PutMapping("/countryLanguage/update/official")
    public CountryLanguageEntity updateLanguageIsOfficial(@RequestBody CountryLanguageEntity countryLanguage) throws CountryLanguageNotFoundException {
        CountryLanguageEntity countryLanguageUpdated = countryLanguageService.getCountryLanguageById(countryLanguage.getId());
        countryLanguageService.updateCountryLanguage(countryLanguageUpdated.getId(), countryLanguage);

        return countryLanguageUpdated;
    }


    //delete
    @DeleteMapping("/countryLanguages/delete")
    public List<CountryLanguageEntity> deleteCountryLanguagesByCountryCode(@RequestParam String code) throws CountryLanguageNotFoundException {
        List<CountryLanguageEntity> countryLanguageEntities = countryLanguageService.getCountryLanguageByCountryCode(code);
        if(countryLanguageEntities.isEmpty()){
            throw new CountryLanguageNotFoundException("with code: " + code);
        }
        for(CountryLanguageEntity ce : countryLanguageEntities)
        {
            countryLanguageService.deleteCountryLanguage(ce.getId());
        }
        return countryLanguageEntities;
    }

//
//    @DeleteMapping("/countryLanguages/delete/language")
//    public List<CountryLanguageEntity> deleteCountryLanguage (@PathVariable String name){
//        List<CountryLanguageEntity> countryLanguageEntities = countryLanguageService.getCountryLanguageByLanguage(name);
//        for(CountryLanguageEntity ce : countryLanguageEntities)
//        {
//            countryLanguageService.deleteCountryLanguage(ce.getId());
//        }
//        return countryLanguageEntities;
//
//    }


}
