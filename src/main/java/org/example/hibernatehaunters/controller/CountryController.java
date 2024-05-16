package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CityEntity;
import org.example.hibernatehaunters.models.entities.CountryEntity;
import org.example.hibernatehaunters.service.CityService;
import org.example.hibernatehaunters.service.CountryService;
import org.example.hibernatehaunters.service.CountrylanguageService;
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
    private final CountrylanguageService countrylanguageService;

    @Autowired
    public CountryController(CountryService countryService, CityService cityService, CountrylanguageService countrylanguageService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.countrylanguageService = countrylanguageService;
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

    @PutMapping("/country/{code}")
    public CountryEntity updateCountry(@PathVariable String code, @RequestBody CountryEntity country) {
        CountryEntity countryUpdated =  countryService.updateCountry(code, country);

        if (countryUpdated == null) {
            // throw error ("country not found cant be updated")
            System.out.println("Country update failed");
        }

        return countryUpdated;
    }

    @DeleteMapping("/country/{code}")
    public ResponseEntity<String> deleteCountryByCode(@PathVariable String code) {
        System.out.println("111" + code);
        Optional<CountryEntity> countryToDelete = countryService.getCountryByCode(code);
        if (countryToDelete.isPresent()) {
            cityService.deleteCityEntitiesByCountryCode(code);
            countrylanguageService.deleteCountrylanguageEntitiesByCountryCode(code);

            boolean isDeleted = countryService.deleteCountry(code);
            if (isDeleted) {
                return new ResponseEntity<>("Country with code " + code + " deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Country with code " + code + " is unable to be deleted", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Country with code " + code + " does not exist.", HttpStatus.NOT_FOUND);
        }
    }
}
