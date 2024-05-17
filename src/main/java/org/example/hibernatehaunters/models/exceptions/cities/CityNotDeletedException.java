package org.example.hibernatehaunters.models.exceptions.cities;

public class CityNotDeletedExceptions extends Exception{
    public CityNotDeletedExceptions(String cityName){
        super("City: " + cityName +" could not be deleted");
    }
}
