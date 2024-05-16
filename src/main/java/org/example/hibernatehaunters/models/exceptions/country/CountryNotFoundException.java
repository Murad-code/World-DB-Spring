package org.example.hibernatehaunters.models.exceptions.country;

public class CountryNotFoundException extends Exception {

    public CountryNotFoundException(String country) {
        super("Country not found: " + country);
    }

}
