package org.example.hibernatehaunters.models.exceptions.cities;

public class CityNotDeletedException extends Exception{
    public CityNotDeletedException(String cityName){
        super("City: " + cityName +" could not be deleted");
    }
}
