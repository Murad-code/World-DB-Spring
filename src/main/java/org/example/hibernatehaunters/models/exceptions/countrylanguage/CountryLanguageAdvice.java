package org.example.hibernatehaunters.models.exceptions.countrylanguage;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CountryLanguageAdvice {

    @ExceptionHandler(CountryLanguageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CountryLanguageErrorResponse> handleCountryLanguageNotFoundException(CountryLanguageNotFoundException e, HttpServletRequest request) {
        CountryLanguageErrorResponse response = new CountryLanguageErrorResponse(request.getRequestURL().toString(), e.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CountryLanguageBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CountryLanguageErrorResponse> handleCountryLanguageBadRequestException(CountryLanguageBadRequestException e, HttpServletRequest request) {
        CountryLanguageErrorResponse response = new CountryLanguageErrorResponse(request.getRequestURL().toString(), e.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
