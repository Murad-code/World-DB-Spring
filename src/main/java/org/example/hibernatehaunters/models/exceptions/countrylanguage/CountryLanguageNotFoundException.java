package org.example.hibernatehaunters.models.exceptions.countrylanguage;

public class CountryLanguageNotFoundException extends Exception {

    public CountryLanguageNotFoundException(String countryCode) {
        super("Could not find Country Language " + countryCode);
    }

    //
    // Bad_Request for incomplete bodies
}
