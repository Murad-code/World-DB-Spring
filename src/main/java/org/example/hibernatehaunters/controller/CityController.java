package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CityEntity;
import org.example.hibernatehaunters.models.respositories.CityEntityRepository;
import org.example.hibernatehaunters.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class CityController {

    private final CityService cityService;
    private final CityEntityRepository cityEntityRepository;

    @Autowired
    public CityController(CityService cityService,
                          CityEntityRepository cityEntityRepository) {
        this.cityService = cityService;
        this.cityEntityRepository = cityEntityRepository;
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


    //Create
    @PostMapping("/city")
    public CityEntity addCity(@RequestBody CityEntity city){
        return cityService.createCity(city);
    }

    //Update
    @PutMapping("city/{id}")
    public CityEntity updateCity(@RequestBody CityEntity cityEntity, @PathVariable Integer id){
        return cityService.updateCity(id, cityEntity);
    }

    //Read
    @GetMapping("/cities")
    public List<CityEntity> getAllCities(){
        return cityService.getAllCities();
    }

    @GetMapping("/city")
    public Optional<CityEntity> getAnyCity(){
        int randomEntry = new Random().nextInt((int)cityEntityRepository.count());
        return cityService.getCityById(randomEntry+1);
    }

    @GetMapping("/city/{id}")
    public Optional<CityEntity> getCityById(@PathVariable Integer id){
        return cityService.getCityById(id);
    }

    //Delete
    @DeleteMapping("city/{id}")
    public Boolean deleteCity(@PathVariable Integer id){
        return cityService.deleteCity(id);
    }

}














