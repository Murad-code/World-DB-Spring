package org.example.hibernatehaunters.models.exceptions.country;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CountryAdvice {

    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CountryErrorResponse> handleCountryNotFoundException(CountryNotFoundException e, HttpServletRequest request) {
        CountryErrorResponse response = new CountryErrorResponse(e.getMessage(), request.getRequestURL().toString(),  404);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CountryCannotBeDeletedException.class)
    public ResponseEntity<CountryErrorResponse> handleCountryCannotBeDeletedException(CountryCannotBeDeletedException e, HttpServletRequest request) {
        CountryErrorResponse response = new CountryErrorResponse(e.getMessage(), request.getRequestURL().toString(),  400);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CountryUpdateBadRequestException.class)
    public ResponseEntity<CountryErrorResponse> handleCountryUpdateBadRequestException(CountryUpdateBadRequestException e, HttpServletRequest request) {
        CountryErrorResponse response = new CountryErrorResponse(e.getMessage(), request.getRequestURL().toString(),  400);
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(CountryBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleCountryBadRequestException(CountryBadRequestException ex) {
        return ex.getMessage();
    }


}
