package org.example.hibernatehaunters.models.exceptions.cities;

public class CityNotUpdatedException extends Exception{
    public CityNotUpdatedException(String cityName){
        super("Could not update City: " + cityName);
    }
}
