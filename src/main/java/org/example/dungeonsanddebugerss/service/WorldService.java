package org.example.dungeonsanddebugerss.service;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.entities.CountryEntity;
import org.example.dungeonsanddebugerss.respositories.CityEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountryEntityRepository;
import org.example.dungeonsanddebugerss.respositories.CountrylanguageEntityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


@Service
public class WorldService {

    private static final Logger logger = Logger.getLogger(WorldService.class.getName());

    private static CityEntityRepository cityEntityRepository;
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
//
//    public List<CountryEntity> findCountryWithMostCity() {
//
//    }
//
//    public int returnCountOfCitiesInCountry() {
//
//    }

    public static List<CityEntity> find5SmallestDistrictsOfCity(String city) {
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

//    public int findCountOfMostPopularLanguage(String countryName) {
//
//    }



}
