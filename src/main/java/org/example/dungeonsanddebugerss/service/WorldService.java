package org.example.dungeonsanddebugerss.service;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.entities.CountryEntity;
import org.example.dungeonsanddebugerss.entities.CountrylanguageEntity;
import org.example.dungeonsanddebugerss.respositories.CityEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountryEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountrylanguageEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WorldService {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    private CityEntityRepository cityEntityRepository;
    private CountryEntityRepository countryEntityRepository;
    private CountrylanguageEntityRepository countrylanguageEntityRepository;


    public WorldService(CityEntityRepository cityEntityRepository, CountryEntityRepository countryEntityRepository, CountrylanguageEntityRepository countrylanguageEntityRepository) {
        this.cityEntityRepository = cityEntityRepository;
        this.countryEntityRepository = countryEntityRepository;
        this.countrylanguageEntityRepository = countrylanguageEntityRepository;
    }

//    public List<CountryEntity> findCountriesWithNoHeadOfState() {
//
//    }
//
//
//    public float findPercentageOfPopulationInLargestCity(String countryName) {
//
//    }

    public List<CountryEntity> findCountryWithMostCity() {
        Map<CountryEntity, Integer> countryCityMap = new HashMap<>();
        List<CountryEntity> countryList = countryEntityRepository.findAll();
          logger.info("Starting to go through countryList...");
        for (CountryEntity country: countryList) {
//          logger.info("Country: " + country.getName());
            String countryCode = country.getCode();
//            logger.info("Country Code: " + countryCode);
            int cityCount = countByCountryCode(countryCode);
//            logger.info("Country code {} has {} cities", countryCode, cityCount);
            countryCityMap.put(country, cityCount);
        }

        int maxCityCount = 0;
        for (int cityCount : countryCityMap.values()) {
            if (cityCount > maxCityCount) {
                maxCityCount = cityCount;
            }
        }

        List<CountryEntity> countryWithMostCities = new ArrayList<>();
        for (Map.Entry<CountryEntity, Integer> countryEntry : countryCityMap.entrySet()) {
            if (countryEntry.getValue() >= maxCityCount) {
                countryWithMostCities.add(countryEntry.getKey());
            }
        }
        logger.info("The country with most cities is " + countryWithMostCities);
        return countryWithMostCities;

    }

    private int countByCountryCode(String countryCode){
         int countryCodeCount = 0;
        List<CityEntity> cityList = cityEntityRepository.findAll();
         for(CityEntity city : cityList ){
//             logger.info("City : {}", city.getName());
             if(city.getCountryCode().getCode().equals(countryCode)){
//                 logger.info("Country Code : {}", city.getCountryCode().getCode());
                 countryCodeCount++;
             }
         }
         return countryCodeCount;

    }
//
//    public int returnCountOfCitiesInCountry() {
//
//    }
//
//    public List<CityEntity> find5SmallestDistrictsOfCity() {
//
//    }
//
//    public int findCountOfMostPopularLanguage(String countryName) {
//
//    }



}
