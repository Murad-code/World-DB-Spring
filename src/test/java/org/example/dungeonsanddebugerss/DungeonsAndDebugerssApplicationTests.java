package org.example.dungeonsanddebugerss;

import org.example.dungeonsanddebugerss.service.WorldService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DungeonsAndDebugerssApplicationTests {

    @Autowired
    private WorldService worldService;

    @Test
    void contextLoads() {
    }


    @Test
    void checkCountryWithNoHeadOfStateIsCalledSanMarino() {
        Assertions.assertEquals("San Marino", worldService.findCountriesWithNoHeadOfState().getFirst().getName());
    }

    @Test
    void checkCountryWithNoHeadOfStateIsNotMoreThanOne() {
        Assertions.assertEquals(1, worldService.findCountriesWithNoHeadOfState().size());
    }



}
