package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CityEntity;
import org.example.hibernatehaunters.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // Example
//    @GetMapping("/city")
//    public CityEntity getCityById(@RequestParam Integer id) {
//
//        Optional<CityEntity> response = cityService.getCityById(id);
//        if (response.isPresent()) {
//            return response.get();
//        }
//
//        // throw error
//        return null;
//    }

}
