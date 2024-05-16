package org.example.hibernatehaunters.models.exceptions.country;

public class CountryBadRequestException extends Throwable {
    public CountryBadRequestException () {
        super("Country field should not be null");
    }
}
