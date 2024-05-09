package org.example.dungeonsanddebugerss;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.example.dungeonsanddebugerss.respositories.CityEntityRepository;
import org.example.dungeonsanddebugerss.service.WorldService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest
class DungeonsAndDebugerssApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Testing that 5 lowest cities are returned")
    void checkFivesCitiesWithLowestPopulationAreReturned(){

        List<CityEntity> cityEntities = new ArrayList<>();

        CityEntity city1 = new CityEntity();
        city1.setName("City1");
        city1.setDistrict("District1");
        city1.setPopulation(2000);
        cityEntities.add(city1);

        CityEntity city4 = new CityEntity();
        city4.setName("City4");
        city4.setDistrict("District1");
        city4.setPopulation(1000);
        cityEntities.add(city4);

        CityEntity city5 = new CityEntity();
        city5.setName("City5");
        city5.setDistrict("District1");
        city5.setPopulation(5000);
        cityEntities.add(city5);

        CityEntity city6 = new CityEntity();
        city6.setName("City6");
        city6.setDistrict("District1");
        city6.setPopulation(3000);
        cityEntities.add(city6);


        CityEntity city2 = new CityEntity();
        city2.setName("City2");
        city2.setDistrict("District1");
        city2.setPopulation(2000);
        cityEntities.add(city2);

        CityEntity city3 = new CityEntity();
        city3.setName("City3");
        city3.setDistrict("District1");
        city3.setPopulation(500);
        cityEntities.add(city3);


        CityEntityRepository cityEntityRepository = Mockito.mock(CityEntityRepository.class);
        Mockito.when(cityEntityRepository.findAll()).thenReturn(cityEntities);


        WorldService worldService = new WorldService(cityEntityRepository, null, null);


        List<CityEntity> result = worldService.find5SmallestDistrictsOfCity("District1");

        assertEquals(5, result.size());
        assertEquals("City3", result.get(0).getName());
        assertEquals("City4", result.get(1).getName());
        assertEquals("City1", result.get(2).getName());
        assertEquals("City2", result.get(3).getName());
        assertEquals("City6", result.get(4).getName());

    }

}
