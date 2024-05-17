package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CountryEntity;
import org.example.hibernatehaunters.models.exceptions.country.CountryBadRequestException;
import org.example.hibernatehaunters.models.exceptions.country.CountryCannotBeDeletedException;

import org.example.hibernatehaunters.models.exceptions.country.CountryNotFoundException;
import org.example.hibernatehaunters.models.exceptions.country.CountryUpdateBadRequestException;
import org.example.hibernatehaunters.service.CityService;
import org.example.hibernatehaunters.service.CountryService;
import org.example.hibernatehaunters.service.CountryLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    private final CountryService countryService;
    private final CityService cityService;
    private final CountryLanguageService countryLanguageService;

    @Autowired
    public CountryController(CountryService countryService, CityService cityService, CountryLanguageService countryLanguageService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.countryLanguageService = countryLanguageService;
    }


    @GetMapping("/countries")
    public List<CountryEntity> getAllCountries() throws CountryNotFoundException {
      List<CountryEntity> countryEntityList =countryService.getAllCountries();
        if (countryEntityList.isEmpty()){
            throw new CountryNotFoundException("none");
        } else {
        return countryService.getAllCountries();
        }
    }

    @GetMapping("/country/{code}")
    public Optional <CountryEntity> getCountryByCode(@PathVariable String code) throws CountryNotFoundException {
    Optional<CountryEntity> countryToFetch  = countryService.getCountryByCode(code);
        if(countryToFetch.isPresent()){
            return countryToFetch;
            } else {
            throw new CountryNotFoundException(code);
        }
    }


    @PostMapping("/country")
    public CountryEntity addCountry(@RequestBody CountryEntity country) throws CountryBadRequestException {
        return countryService.createCountry(country);

    }

    @PutMapping("/country/{code}")
    public CountryEntity updateCountry(@PathVariable String code, @RequestBody CountryEntity country) throws CountryNotFoundException, CountryUpdateBadRequestException {
        CountryEntity countryUpdated = countryService.updateCountry(code, country);

        return countryUpdated;
    }

    @DeleteMapping("/country/{code}")
    public ResponseEntity<String> deleteCountryByCode(@PathVariable String code) throws CountryNotFoundException, CountryCannotBeDeletedException {
        Optional<CountryEntity> countryToDelete = countryService.getCountryByCode(code);
        if (countryToDelete.isPresent()) {
            cityService.deleteCityEntitiesByCountryCode(code);
            countryLanguageService.deleteCountryLanguageEntitiesByCountryCode(code);

            boolean isDeleted = countryService.deleteCountry(code);
            if (isDeleted) {
                return new ResponseEntity<>("Country with code " + code + " deleted successfully.", HttpStatus.OK);
            } else {
                throw new CountryCannotBeDeletedException(code);
            }
        } else {
            throw new CountryNotFoundException(code);
        }
    }
}
