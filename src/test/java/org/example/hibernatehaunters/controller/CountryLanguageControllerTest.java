package org.example.hibernatehaunters.controller;

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
}
