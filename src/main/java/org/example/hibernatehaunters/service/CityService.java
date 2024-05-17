package org.example.hibernatehaunters.service;

import org.example.hibernatehaunters.models.entities.CityEntity;
import org.example.hibernatehaunters.models.exceptions.cities.CityNotCreatedException;
import org.example.hibernatehaunters.models.respositories.CityEntityRepository;
import org.hibernate.TransientPropertyValueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityEntityRepository cityEntityRepository;

    public CityService(CityEntityRepository cityEntityRepository) {
        this.cityEntityRepository = cityEntityRepository;
    }

    public CityEntity createCity(CityEntity city){
        return cityEntityRepository.save(city);
    }

    public List<CityEntity> getAllCities() {
        return cityEntityRepository.findAll();
    }

    public Optional<CityEntity> getCityById(Integer id) {
        return cityEntityRepository.findById(id);
    }

    public CityEntity updateCity(Integer id, CityEntity updatedCity) {
        Optional<CityEntity> cityOptional = cityEntityRepository.findById(id);
        if (cityOptional.isPresent()) {
            CityEntity existingCity = cityOptional.get();
            existingCity.setName(updatedCity.getName());
            existingCity.setCountryCode(updatedCity.getCountryCode());
            existingCity.setDistrict(updatedCity.getDistrict());
            existingCity.setPopulation(updatedCity.getPopulation());

            return cityEntityRepository.save(existingCity);
        } else {
            return null;
        }
    }

    public boolean deleteCity(Integer id) {
        Optional<CityEntity> cityOptional = cityEntityRepository.findById(id);
        if (cityOptional.isPresent()) {
            cityEntityRepository.delete(cityOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void deleteCityEntitiesByCountryCode(String countryCode) {
        cityEntityRepository.deleteCityEntitiesByCountryCode_Code(countryCode);
    }
}