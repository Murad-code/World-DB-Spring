package org.example.dungeonsanddebugerss.service;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.entities.CountryEntity;
import org.example.dungeonsanddebugerss.entities.CountrylanguageEntity;
import org.example.dungeonsanddebugerss.respositories.CityEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountryEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountrylanguageEntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    }



}
