package org.example.hibernatehaunters.models.exceptions.country;

public class CountryUpdateBadRequestException extends Exception {
    public CountryUpdateBadRequestException(String country) {
        super("Country update failed: " + country);
    }
}
