package org.example.hibernatehaunters.controller;

import org.example.hibernatehaunters.models.entities.CountryLanguageEntity;
import org.example.hibernatehaunters.models.entities.CountryLanguageIdEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CountryLanguageControllerTest {

    private WebTestClient webClient;

    @BeforeEach
    public void setUp(){
        webClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    @Test
    @DisplayName("check that the status code is 200")
    public void checkThatTheStatusCodeIs200() {

        webClient.get().uri("countryLanguage?code=USA").exchange().expectStatus().isOk();

    }

    @Test
    @DisplayName("check that endpoint GET /countryLanguages status code is 200")
    public void checkThatEndpointGetCountryLanguagesStatusCodeIs200() {

        webClient.get().uri("countryLanguages").exchange().expectStatus().isOk();
    }

    @Test
    @DisplayName("check that endpoint PUT /countryLanguages/update status code is 201")
    public void checkThatEndpointPutCountryLanguagesUpdateStatusCodeIs201() {


        //webClient.put().uri("countryLanguage/update/official").body();
                
    }

    @Test
    @DisplayName("check that status code is 404")
    public void checkThatStatusCodeIs404() {

        webClient.get().uri("/countryLanguage?code=AAA").exchange().expectStatus().isNotFound();
    }

    @Test
    @DisplayName("check that status code for /countryLanguage/delete is 404")
    public void checkThatStatusCodeForCountryLanguageDeleteIs404() {
        webClient.get().uri("/countryLanguage/delete?code=BBB").exchange().expectStatus().isNotFound();
    }

}
