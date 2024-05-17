package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CountryEntity;
import org.example.hibernatehaunters.models.respositories.CountryEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

@SpringBootTest
@Transactional
public class CountryControllerTests {
    private WebTestClient webTestClient;
    private CountryEntity countryEntity;


    @Autowired
    private CountryController countryController;
    @Autowired
    private CountryEntityRepository countryEntityRepository;

    @BeforeEach
    public void setup() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

        countryEntity = new CountryEntity();
        countryEntity.setCode("AND");
        countryEntity.setName("Andorra");
        countryEntity.setContinent("Europe");
        countryEntity.setRegion("Southern Europe");
        countryEntity.setSurfaceArea(new BigDecimal("468.00"));
        countryEntity.setIndepYear((short) 1278);
        countryEntity.setPopulation(78000);
        countryEntity.setLifeExpectancy(new BigDecimal("83.5"));
        countryEntity.setGnp(new BigDecimal("1630.00"));
        countryEntity.setGNPOld(new BigDecimal("100.00"));
        countryEntity.setLocalName("Andorra");
        countryEntity.setGovernmentForm("Parliamentary Coprincipality");
        countryEntity.setHeadOfState("Murad");
        countryEntity.setCapital(55);
        countryEntity.setCode2("AD");

        countryEntityRepository.save(countryEntity);
    }

    @Test
    @DisplayName("Test Update Country is Successful")
    public void testUpdateCountryIsSuccessful() {
        webTestClient.put().uri("/country/AIA")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"code\":\"AND\",\"name\":\"Andorra\",\"continent\":\"Europe\",\"region\":\"Southern Europe\",\"surfaceArea\":468.00,\"indepYear\":1278,\"population\":100000,\"lifeExpectancy\":83.5,\"gnp\":1630.00,\"gnpold\":100.00,\"localName\":\"Andorra\",\"governmentForm\":\"Parliamentary Coprincipality\",\"headOfState\":\"Murad\",\"capital\":55,\"code2\":\"AD\"}")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.code").isEqualTo("AIA")
                .jsonPath("$.name").isEqualTo("Andorra")
                .jsonPath("$.continent").isEqualTo("Europe")
                .jsonPath("$.region").isEqualTo("Southern Europe")
                .jsonPath("$.surfaceArea").isEqualTo(468.00)
                .jsonPath("$.indepYear").isEqualTo(1278)
                .jsonPath("$.population").isEqualTo(100000)
                .jsonPath("$.lifeExpectancy").isEqualTo(83.5)
                .jsonPath("$.gnp").isEqualTo(1630.00)
                .jsonPath("$.gnpold").isEqualTo(100.00)
                .jsonPath("$.localName").isEqualTo("Andorra")
                .jsonPath("$.governmentForm").isEqualTo("Parliamentary Coprincipality")
                .jsonPath("$.headOfState").isEqualTo("Murad")
                .jsonPath("$.capital").isEqualTo(55)
                .jsonPath("$.code2").isEqualTo("AD");
    }

//    @Test
//    @DisplayName("Check that country is deleted successfully")
//    void checkThatCountryIsDeletedSuccessfully() {
//        webTestClient
//                .delete()
//                .uri("http://localhost:8080/country/AND")
//                .exchange()
//                .expectStatus()
//                .is2xxSuccessful();
//    }

}
