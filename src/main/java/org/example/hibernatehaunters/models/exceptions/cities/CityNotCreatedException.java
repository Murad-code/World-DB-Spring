package org.example.hibernatehaunters.models.exceptions.cities;

public class CityNotCreatedException extends Exception{
    public CityNotCreatedException(String cityName){
        super("City: " + cityName +" could not be created");
    }
}
