package org.example.hibernatehaunters;

import org.example.hibernatehaunters.service.CityService;
import org.example.hibernatehaunters.service.CountryLanguageService;
import org.example.hibernatehaunters.service.CountryService;
import org.example.hibernatehaunters.service.WorldService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;


@SpringBootApplication
public class HibernateHauntersApplication {

    private static final Logger logger = Logger.getLogger("Spring Logger");

    public static void main(String[] args) {
        SpringApplication.run(HibernateHauntersApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(WorldService worldService, CityService cityService, CountryService countryService, CountryLanguageService countrylanguageService) {
        return args -> {
//                logger.info(String.valueOf(worldService.findCountryWithMostCity()));
//            //logger.info(String.valueOf(worldService.findCountryWithMostCity()));
//            List<CityEntity> result = worldService.find5SmallestDistrictsOfCity("Noord-Holland");
//            logger.info(String.valueOf(worldService.returnNumOfCities()));
//            logger.info(String.valueOf(result));
//            logger.info(cityService.getAllCities().toString());
//            logger.info(countryService.getCountryByCode("ABW").toString());
//            logger.info(countrylanguageService.getAllCountryLanguages().toString())
        };
    }

}
