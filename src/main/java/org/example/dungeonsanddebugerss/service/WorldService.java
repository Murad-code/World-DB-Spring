package org.example.dungeonsanddebugerss.service;

import org.antlr.v4.runtime.misc.LogManager;
import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.entities.CountryEntity;
import org.example.dungeonsanddebugerss.entities.CountrylanguageEntity;
import org.example.dungeonsanddebugerss.respositories.CityEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountryEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountrylanguageEntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


@Service
public class WorldService {

    private final Logger logger = Logger.getLogger("Spring Logger");

    private CityEntityRepository cityEntityRepository;
    private CountryEntityRepository countryEntityRepository;
    private CountrylanguageEntityRepository countrylanguageEntityRepository;


    public WorldService(CityEntityRepository cityEntityRepository, CountryEntityRepository countryEntityRepository, CountrylanguageEntityRepository countrylanguageEntityRepository) {
        this.cityEntityRepository = cityEntityRepository;
        this.countryEntityRepository = countryEntityRepository;
        this.countrylanguageEntityRepository = countrylanguageEntityRepository;
    }

    public List<CountryEntity> findCountriesWithNoHeadOfState() {
        return null;
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

    public List<CountryEntity> findCountryWithMostCity() {
        return null;
    }

    public int returnCountOfCitiesInCountry() {
        return 0;
    }

    public List<CityEntity> find5SmallestDistrictsOfCity() {
        return null;
    }

    public int findCountOfMostPopularLanguage(String countryName) {
        return 0;
    }



}
