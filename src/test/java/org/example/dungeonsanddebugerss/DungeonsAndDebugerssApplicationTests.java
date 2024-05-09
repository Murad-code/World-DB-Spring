package org.example.dungeonsanddebugerss;

import org.example.dungeonsanddebugerss.service.WorldService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
  
    @Nested
    class testingFindCountOfMostPopularLanguage{
        @Test
        @DisplayName("Given Afghanistan, the number of people who speak the most popular official language is 11905280 ")
        void givenAfghanistanTheNumberOfPeopleWhoSpeakTheMostPopularOfficialLanguageIs11905280() {
            Assertions.assertEquals(11905280,worldService.findCountOfMostPopularLanguage("Afghanistan"));
        }

        @Test
        @DisplayName("Given a country doesn't exists, return negative 1")
        void givenACountryDoesnTExistsReturnNegative1() {
            Assertions.assertEquals(-1,worldService.findCountOfMostPopularLanguage("BABYLON"));
        }

        @Test
        @DisplayName("Given Aruba, the number of people who speak the most popular official language is ")
        void givenArubaTheNumberOfPeopleWhoSpeakTheMostPopularOfficialLanguageIs() {
            Assertions.assertEquals(5459,worldService.findCountOfMostPopularLanguage("Aruba"));
        }

        @Test
        @DisplayName("Given Angola, country has no official language so it should return -1")
        void givenAngolaCountryHasNoOfficialLanguageSoItShouldReturn1() {
            Assertions.assertEquals(-1,worldService.findCountOfMostPopularLanguage("Angola"));
        }


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
