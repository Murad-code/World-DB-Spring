package org.example.hibernatehaunters.models.exceptions.cities;


public class CityNotFoundException extends Exception{
    public CityNotFoundException(String cityName){
            super("Could not find City: " + cityName);
    }
}


