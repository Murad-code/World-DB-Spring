package org.example.dungeonsanddebugerss;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.service.WorldService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class DungeonsAndDebugerssApplication {

    private static final Logger logger = Logger.getLogger("Spring Logger");

    public static void main(String[] args) {
        SpringApplication.run(DungeonsAndDebugerssApplication.class, args);

//    @Bean
//    public CommandLineRunner run;() {
//        return args -> {
//
//            List<CityEntity> result = WorldService.find5SmallestDistrictsOfCity("Noord-Holland");
//
//        };
//
//    }


    }
    @Bean
    public CommandLineRunner runner(WorldService worldService){
        return args -> logger.info(String.valueOf(worldService.findCountryWithMostCity()));
    }

}
