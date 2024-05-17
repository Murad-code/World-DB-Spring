package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CityEntity;
import org.example.hibernatehaunters.models.exceptions.cities.CityNotCreatedException;
import org.example.hibernatehaunters.models.exceptions.cities.CityNotDeletedException;
import org.example.hibernatehaunters.models.exceptions.cities.CityNotFoundException;
import org.example.hibernatehaunters.models.exceptions.cities.CityNotUpdatedException;
import org.example.hibernatehaunters.models.respositories.CityEntityRepository;
import org.example.hibernatehaunters.service.CityService;
import org.hibernate.TransientPropertyValueException;
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


    //Create
    @PostMapping("/city")
    public CityEntity addCity(@RequestBody CityEntity city)
            throws CityNotCreatedException{
        try{
            return cityService.createCity(city);
        }
        catch (Exception e)
        {
            throw new CityNotCreatedException(city.getName());
        }
    }

    //Update
    @PutMapping("/city/{id}")
    public CityEntity updateCity(@RequestBody CityEntity cityEntity, @PathVariable Integer id)
            throws CityNotUpdatedException {
        try{
            return cityService.updateCity(id, cityEntity);
        }
        catch (Exception e)
        {
            throw new CityNotUpdatedException(cityEntity.getId().toString());
        }
    }

    //Read
    @GetMapping("/cities")
    public List<CityEntity> getAllCities() throws CityNotFoundException {
        List<CityEntity> cities = cityService.getAllCities();
        if(cities.isEmpty()){
            throw new CityNotFoundException("All");
        }else{
            return cityService.getAllCities();
        }

    }

    @GetMapping("/city")
    public Optional<CityEntity> getAnyCity() throws CityNotFoundException{
        int randomEntry = new Random().nextInt((int)cityEntityRepository.count());
        Optional<CityEntity> cityEntity = cityService.getCityById(randomEntry+1);
        if(cityEntity.isPresent()) {
            return cityService.getCityById(randomEntry+1);
        } else {
            throw new CityNotFoundException(String.valueOf(randomEntry+1));
        }
    }

    @GetMapping("/city/{id}")
    public Optional<CityEntity> getCityById(@PathVariable Integer id) throws CityNotFoundException {
        Optional<CityEntity> cityEntity = cityService.getCityById(id);
        if(cityEntity.isPresent()) {
            return cityService.getCityById(id);
        } else {
            throw new CityNotFoundException(id.toString());
        }
    }



    //Delete
    @DeleteMapping("city/{id}")
    public Boolean deleteCity(@PathVariable Integer id){
        if(cityService.deleteCity(id) != null)
        return cityService.deleteCity(id);
    @DeleteMapping("/city/{id}")
    public Boolean deleteCity(@PathVariable Integer id) throws CityNotDeletedException{
        boolean result = cityService.deleteCity(id);
        if(result)
        {
            return true;
        }
        else{
            throw new CityNotDeletedException(id.toString());
        }
    }

}














