package org.example.dungeonsanddebugerss;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.service.CityService;
import org.example.dungeonsanddebugerss.service.CountryService;
import org.example.dungeonsanddebugerss.service.CountrylanguageService;
import org.example.dungeonsanddebugerss.service.WorldService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

import java.util.List;


@SpringBootApplication
public class DungeonsAndDebugerssApplication {

    private static final Logger logger = Logger.getLogger("Spring Logger");

    public static void main(String[] args) {
        SpringApplication.run(DungeonsAndDebugerssApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(WorldService worldService, CityService cityService, CountryService countryService, CountrylanguageService countrylanguageService) {
        return args -> {
//            logger.info("Countries with no head of state: " + worldService.findCountriesWithNoHeadOfState());
//            logger.info("Percentage of population in the largest city at Netherlands: " + worldService.findPercentageOfPopulationInLargestCity("Netherlands"));
//            logger.info("5 Smallest districts of the city Noord-Holland: " + worldService.find5SmallestDistrictsOfCity("Noord-Holland"));
//            logger.info("The number of people who speaks the most popular official language of South Korea is: " + worldService.findCountOfMostPopularLanguage("South Korea"));
//            logger.info("Country with the most cities: " + worldService.findCountryWithMostCity() + " with " + worldService.returnNumOfCities() + " cities");
//            logger.info(cityService.getAllCities().toString());
//            logger.info(countryService.getCountryByCode("ABW").toString());
//            logger.info(countrylanguageService.getAllCountryLanguages().toString());
        };
    }

}
