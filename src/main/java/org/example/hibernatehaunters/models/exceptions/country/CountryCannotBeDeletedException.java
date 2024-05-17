package org.example.hibernatehaunters.models.exceptions.country;

public class CountryCannotBeDeletedException extends Exception {
    public CountryCannotBeDeletedException(String country) {
        super("Country could not be deleted: " + country);
    }
}
