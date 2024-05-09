package org.example.dungeonsanddebugerss;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.example.dungeonsanddebugerss.service.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DungeonsAndDebugerssApplicationTests {

    @Autowired
    WorldService worldService;

    @Test
    void contextLoads() {
    }


    @Test
    @DisplayName("Given invalid country, get percentage returns 0.")
    void givenInvalidCountryGetPercentageReturns0() {
        float expected = 0;
        float result = worldService.findPercentageOfPopulationInLargestCity("Not A Country");
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Given empty country, get percentage returns 0.")
    void givenEmptyCountryGetPercentReturns0() {
        float expected = 0;
        float result = worldService.findPercentageOfPopulationInLargestCity("");
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Given valid country, return the percentage of the population of the highest city pop.")
    void givenValidCountryReturnThePercentageOfThePopulation() {
        float expected = 4.609178f;
        float result = worldService.findPercentageOfPopulationInLargestCity("Netherlands");
        Assertions.assertEquals(expected, result);
    }
}
