package org.example.hibernatehaunters.models.exceptions.cities;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CityAdvice {

    //get
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CityErrorResponse> cityNotFoundHandler(
            CityNotFoundException e,
            HttpServletRequest request){
                CityErrorResponse response = new CityErrorResponse(
                        e.getMessage(), 400, request.getRequestURL().toString());

                return ResponseEntity.badRequest().body(response);

    }

//    @ExceptionHandler(CityNotCreatedException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<CityNotCreatedException> cityNotCreatedHandler(
//            CityNotCreatedException e,
//            HttpServletRequest request){
//        CityErrorResponse response = new CityErrorResponse(
//                e.getMessage(), 400, request.getRequestURL().toString());
//
//        return ResponseEntity.badRequest().body(response);
//
//    }

    //post
    @ExceptionHandler(CityNotCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CityErrorResponse> cityNotCreatedHandler(
            CityNotCreatedException e,
            HttpServletRequest request) {
        CityErrorResponse response = new CityErrorResponse(
                e.getMessage(), 400, request.getRequestURL().toString());

        return ResponseEntity.badRequest().body(response);
    }

    //put
    @ExceptionHandler(CityNotUpdatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CityErrorResponse> cityNotUpdatedHandler(
            CityNotUpdatedException e,
            HttpServletRequest request) {
        CityErrorResponse response = new CityErrorResponse(
                e.getMessage(), 400, request.getRequestURL().toString());

        return ResponseEntity.badRequest().body(response);
    }

    //delete
    @ExceptionHandler(CityNotDeletedExceptions.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CityErrorResponse> cityNotDeletedHandler(
            CityNotDeletedExceptions e,
            HttpServletRequest request) {
        CityErrorResponse response = new CityErrorResponse(
                e.getMessage(), 400, request.getRequestURL().toString());

        return ResponseEntity.badRequest().body(response);
    }



}
