package org.example.hibernatehaunters.models.exceptions.country;

public record CountryErrorResponse(String message, String url, int statusCode) {
}
