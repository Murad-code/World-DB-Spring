package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CityEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

@SpringBootTest
@Transactional
public class CityControllerTests {

    private WebTestClient webTestClient;

    @Autowired
    private CityController cityController;

    @BeforeEach
    @Transactional
    public void setup(){
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    //get city/{id}
    @Test
    @DisplayName("Check that the first city is Kabul")
    void checkThatTheFirstCityIsKabul() {
        webTestClient
                .get()
                .uri("/city/1")
                .exchange()
                .expectBody(CityEntity.class)
                .value(city -> Assertions.assertEquals("Kabul", city.getName()));

    }

    //get city (random)
    @Test
    @DisplayName("Check that get city returns any city")
    void checkThatGetCityReturnsAnyCity() {
        webTestClient
                .get()
                .uri("city")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

    }

    //get cities
    @Test
    @DisplayName("Check that get cities returns 200")
    void checkThatGetCitiesReturns200() {
        webTestClient
                .get()
                .uri("cities")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

    }
    



}
