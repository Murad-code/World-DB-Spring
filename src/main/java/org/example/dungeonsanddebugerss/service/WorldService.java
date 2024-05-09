package org.example.dungeonsanddebugerss.service;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.entities.CountryEntity;
import org.example.dungeonsanddebugerss.entities.CountrylanguageEntity;
import org.example.dungeonsanddebugerss.entities.CountrylanguageEntityId;
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
import java.util.Collections;
import java.util.logging.Logger;


@Service
public class WorldService {
    Logger logger = LoggerFactory.getLogger(Controller.class);
    private CityEntityRepository cityEntityRepository;
    private CountryEntityRepository countryEntityRepository;
    private CountrylanguageEntityRepository countrylanguageEntityRepository;

    private  static final Logger logger = Logger.getLogger("Spring Logger");


    public WorldService(CityEntityRepository cityEntityRepository, CountryEntityRepository countryEntityRepository, CountrylanguageEntityRepository countrylanguageEntityRepository) {
        this.cityEntityRepository = cityEntityRepository;
        this.countryEntityRepository = countryEntityRepository;
        this.countrylanguageEntityRepository = countrylanguageEntityRepository;
    }

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

    public List<CountryEntity> findCountriesWithNoHeadOfState() {
        logger.info("Starting findCountriesWithNoHeadOfState method");
        List<CountryEntity> allCountries = countryEntityRepository.findAll();
        logger.fine("Collected all countries");
        List<CountryEntity> noHeadOfStateCountries = new ArrayList<>();

        logger.fine("Looping through countries to find no head of state");
        for (CountryEntity countryEntity : allCountries) {
            if (countryEntity.getHeadOfState() == null) {
                logger.finer("Found no head of state, adding country to return list");
                noHeadOfStateCountries.add(countryEntity);
            }
        }

        logger.info("Finished findCountriesWithNoHeadOfState method");
        return noHeadOfStateCountries;
    }


    public float findPercentageOfPopulationInLargestCity(String countryName) {
    }

    public List<CountryEntity> findCountryWithMostCity() {
    }

    public int returnCountOfCitiesInCountry() {
    }

    public List<CityEntity> find5SmallestDistrictsOfCity() {
    }

    public int findCountOfMostPopularLanguage(String countryName) {
        int totalPopulation;
        String countryCode;

        CountryEntity country = findCountry(countryName);
        if(country != null){
            countryCode = country.getCode();
            totalPopulation = country.getPopulation();
        }else{
            return -1;
        }

        ArrayList<Integer> peopleWhoSpeakLanguage = new ArrayList<Integer>();
        for(CountrylanguageEntity language : getAllLanguageCountrySpeaks(countryCode)){
            if(language.getIsOfficial().equals("T")){
                peopleWhoSpeakLanguage.add(Math.round(totalPopulation * (language.getPercentage().floatValue()/100)));
            }
        }

        if(peopleWhoSpeakLanguage.isEmpty()){
            return -1;
        }

        Collections.sort(peopleWhoSpeakLanguage);
        return peopleWhoSpeakLanguage.getLast();
    }

    private CountryEntity findCountry(String countryName){
        for(CountryEntity country : countryEntityRepository.findAll()) {
            if(country.getName().equals(countryName)) {
                return country;
            }
        }
        return null;
    }

    private List<CountrylanguageEntity> getAllLanguageCountrySpeaks(String countryCode){
        List<CountrylanguageEntity> countries = new ArrayList<>();
        for(CountrylanguageEntity countrylanguageEntity : countrylanguageEntityRepository.findAll()){
            if(countrylanguageEntity.getCountryCode().getCode().equals(countryCode)){
                countries.add(countrylanguageEntity);
            }
        }
        return countries;
    }

}
