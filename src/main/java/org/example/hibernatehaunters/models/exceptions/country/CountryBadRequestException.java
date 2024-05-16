package org.example.hibernatehaunters.models.exceptions.country;

public class CountryBadRequestException extends RuntimeException {
    public CountryBadRequestException () {
        super("Country fields: code, name, continent, region, surface area , population, local name, government form and code2 are mandatory. Please provide a valid value.");

    }
}
