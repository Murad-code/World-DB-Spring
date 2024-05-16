package org.example.hibernatehaunters.models.exceptions.countrylanguage;

public class CountryLanguageBadRequestException extends Exception{
    public CountryLanguageBadRequestException(String message) {
        super("Invalid request body: " + message);
    }
}
