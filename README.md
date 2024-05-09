## employee-sql-dao
World-DB-Spring 'Dungeons and Debuggerss' consisting of Imogen, Murad, Selam, Irina, Howard, Patryk and Phoenix 👋.

## Project Overview
This project creates a Java applicaiton that uses an SQL database which contains a list of countries and cities, along with other details.
This applicaiton allows users to query certain fields within the database, while following the spring architecture layout: Entities->Repository->Service 

## Acceptance Criteria
- Interact with the MySQL World Database
- Use Spring JPA to connect and communicate with the Database
- Use basic CRUD operations
- Provide multiple types of search methods
- Implement the service layer in your application
- Use WebMVCTests to ensure your repositories work correctly


## Dependencies
JDK 21, JUnit, Mockito


## How to Use the Project 

Setup: Ensure you have Java installed on your system. 

    Fork this repository
    Clone the forked repository and import it into yout preferred Java IDE
    Add your contributions (code or documentation)
    Commit and push
    Wait for pull request to be merged

## How to use the Program 

Open the project directory: "Dungeons and Debuggerss" and open the class "App". Ensure the spring boot application is running:

```
  public static void main(String[] args) {
        SpringApplication.run(DungeonsAndDebugerssApplication.class, args);
    }
```
Within the main method you can query the SQL database withthe following methods within the bean. Comment out the methods you dont require.
```
            logger.info(String.valueOf(worldService.findCountryWithMostCity()));
            List<CityEntity> result = worldService.find5SmallestDistrictsOfCity("Noord-Holland");
            logger.info(String.valueOf(worldService.returnNumOfCities()));
            logger.info(String.valueOf(result));
            logger.info(cityService.getAllCities().toString());
            logger.info(countryService.getCountryByCode("ABW").toString());
            logger.info(countrylanguageService.getAllCountryLanguages().toString());
```

You can use any combination or frequency of these methods, and by running the program the results of each search will be shown in the console.

To enhance maintainability we created logging functionality using java.util.logging. Our colour-coded logger allows you to easily track the flow of the program, record the state when an important event happens and capture errors or exceptions that occur during runtime. This can be used through the Log class and it's static methods.


##  

📫 If you encounter any bugs, please open up an issue to let us know.
Alternatively, we welcome suggestions for any updates or improvements you would like to see! 
