package org.example.hibernatehaunters.service;

import org.example.hibernatehaunters.models.entities.CityEntity;
import org.example.hibernatehaunters.models.entities.CountryEntity;

import org.example.hibernatehaunters.models.entities.CountryLanguageEntity;
import org.example.hibernatehaunters.models.respositories.CityEntityRepository;
import org.example.hibernatehaunters.models.respositories.CountryEntityRepository;
import org.example.hibernatehaunters.models.respositories.CountryLanguageEntityRepository;


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

    private final Logger logger = Logger.getLogger("Spring Logger");

    private CityEntityRepository cityEntityRepository;
    private CountryEntityRepository countryEntityRepository;
    private CountryLanguageEntityRepository countrylanguageEntityRepository;

    public WorldService(CityEntityRepository cityEntityRepository, CountryEntityRepository countryEntityRepository, CountryLanguageEntityRepository countrylanguageEntityRepository) {
        this.cityEntityRepository = cityEntityRepository;
        this.countryEntityRepository = countryEntityRepository;
        this.countrylanguageEntityRepository = countrylanguageEntityRepository;
    }

    public float findPercentageOfPopulationInLargestCity(String countryName) {
        int countryPopTotal = 0;
        String countryCode = "";

        //for each country
        for(CountryEntity c : countryEntityRepository.findAll())
        {
            if(countryName.equals(c.getName()))
            {
                countryPopTotal = c.getPopulation();
                logger.info("Country: " + c.getName() + " | Population: " + countryPopTotal);
                countryCode = c.getCode();
            }
        }

        if(!countryCode.isEmpty())
        {
            int highestPop = 0;
            String highestPopCityName = "";
            for(CityEntity c : cityEntityRepository.findAll())
            {
                if(countryCode.equals(c.getCountryCode().getCode()))
                {
                    if(highestPop < c.getPopulation())
                    {
                        highestPop = c.getPopulation();
                        highestPopCityName = c.getName();
                    }
                }
            }
            float result = (float) highestPop / countryPopTotal;
            result *= 100;
            logger.info("Highest city pop: " + highestPopCityName + " | Population: " + highestPop);
            logger.info("Percentage: " + result);
            return result;
        }
        return 0;
    }

    public List<CityEntity> find5SmallestDistrictsOfCity(String city) {
        List<CityEntity> cityEntities = cityEntityRepository.findAll();
        ArrayList<CityEntity> lowest = new ArrayList<>();

        for(CityEntity cityEntity : cityEntities) {
            if(cityEntity.getDistrict().equals(city)){
                lowest.add(cityEntity);
            }
        }


        lowest.sort(Comparator.comparing(CityEntity::getPopulation));

        List<CityEntity> top5Cities = lowest.subList(0, Math.min(5, lowest.size()));

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
        logger.info("The country with most cities is " + countryWithMostCities);
            }
        }
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

    public int returnNumOfCities(){
        ArrayList<CityEntity>  citiesInCountry = new ArrayList<>();
        List<CountryEntity> countryWithMostCities = findCountryWithMostCity();
        logger.info("Country with most cities is " + countryWithMostCities);
        String countryCode = countryWithMostCities.getFirst().getCode();
        logger.info("Country code for country with most cities is " + countryCode);

         List<CityEntity> cityList = cityEntityRepository.findAll();
          for(CityEntity city: cityList){
              if(city.getCountryCode().getCode().equals(countryCode)){
                  logger.info("City in country with most cities is " + city.getName());
                  citiesInCountry.add(city);

              }

          }

        return citiesInCountry.size();
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
        for(CountryLanguageEntity language : getAllLanguageCountrySpeaks(countryCode)){
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

    private List<CountryLanguageEntity> getAllLanguageCountrySpeaks(String countryCode){
        List<CountryLanguageEntity> countries = new ArrayList<>();
        for(CountryLanguageEntity countrylanguageEntity : countrylanguageEntityRepository.findAll()){
            if(countrylanguageEntity.getCountryCode().getCode().equals(countryCode)){
                countries.add(countrylanguageEntity);
            }
        }
        return countries;
    }

}
