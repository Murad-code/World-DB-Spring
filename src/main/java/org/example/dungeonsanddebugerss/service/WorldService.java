package org.example.dungeonsanddebugerss.service;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.entities.CountryEntity;

import org.example.dungeonsanddebugerss.entities.CountrylanguageEntity;
import org.example.dungeonsanddebugerss.respositories.CityEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountryEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountrylanguageEntityRepository;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;


@Service
public class WorldService {

    private CityEntityRepository cityEntityRepository;
    private CountryEntityRepository countryEntityRepository;
    private CountrylanguageEntityRepository countrylanguageEntityRepository;

    private  static final Logger logger = Logger.getLogger("Spring Logger");


    public WorldService(CityEntityRepository cityEntityRepository, CountryEntityRepository countryEntityRepository, CountrylanguageEntityRepository countrylanguageEntityRepository) {
        this.cityEntityRepository = cityEntityRepository;
        this.countryEntityRepository = countryEntityRepository;
        this.countrylanguageEntityRepository = countrylanguageEntityRepository;
    }

    public List<CityEntity> find5SmallestDistrictsOfCity(String city) {
        List<CityEntity> cityEntities = cityEntityRepository.findAll();
        ArrayList<CityEntity> lowest = new ArrayList<>();

        for(CityEntity cityEntity : cityEntities) {
            if(cityEntity.getDistrict().equals(city)){
                lowest.add(cityEntity);
            }
        }
        Comparator<CityEntity> populationComparator = new Comparator<CityEntity>() {
            @Override
            public int compare(CityEntity city1, CityEntity city2) {
                return Integer.compare(city1.getPopulation(), city2.getPopulation());
            }
        };

        Collections.sort(cityEntities, populationComparator);

        List<CityEntity> top5Cities = cityEntities.subList(0, Math.min(5, cityEntities.size()));

        for (int i = 0; i < top5Cities.size(); i++) {
            CityEntity cities = top5Cities.get(i);
            logger.info("City " + (i+1) + ": " + cities.getName() + ", Population: " + cities.getPopulation());
        }

        return top5Cities;
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
    }

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




    public int returnCountOfCitiesInCountry() {
        return 0;
    }



    public int findCountOfMostPopularLanguage(String countryName){
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
