package org.example.hibernatehaunters.service;

import org.example.hibernatehaunters.models.entities.CountryEntity;
import org.example.hibernatehaunters.models.exceptions.country.CountryNotFoundException;
import org.example.hibernatehaunters.models.exceptions.country.CountryUpdateBadRequestException;
import org.example.hibernatehaunters.models.exceptions.country.CountryBadRequestException;
import org.example.hibernatehaunters.models.respositories.CountryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryEntityRepository countryRepository;

    @Autowired
    public CountryService(CountryEntityRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryEntity createCountry(CountryEntity country) throws CountryBadRequestException {
        if(country.getCode() ==  null ||
                country.getName() == null ||
                country.getContinent() == null ||
                country.getRegion() == null ||
                country.getSurfaceArea() == null ||
                country.getPopulation() == null ||
                country.getLocalName() == null ||
                country.getGovernmentForm()== null||
                country.getCode2()==null){
            throw new CountryBadRequestException();
        }
        return countryRepository.save(country);
    }

    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<CountryEntity> getCountryByCode(String code) {
        return countryRepository.findById(code);
    }

    public CountryEntity updateCountry(String code, CountryEntity updatedCountry) throws CountryUpdateBadRequestException, CountryNotFoundException {
        Optional<CountryEntity> countryOptional = countryRepository.findById(code);
        if (countryOptional.isPresent()) {
            System.out.println("22222: " + countryOptional.get());
            try {
                CountryEntity existingCountry = countryOptional.get();
                existingCountry.setName(updatedCountry.getName());
                existingCountry.setContinent(updatedCountry.getContinent());
                existingCountry.setRegion(updatedCountry.getRegion());
                existingCountry.setSurfaceArea(updatedCountry.getSurfaceArea());
                existingCountry.setIndepYear(updatedCountry.getIndepYear());
                existingCountry.setPopulation(updatedCountry.getPopulation());
                existingCountry.setLifeExpectancy(updatedCountry.getLifeExpectancy());
                existingCountry.setGnp(updatedCountry.getGnp());
                existingCountry.setGNPOld(updatedCountry.getGNPOld());
                existingCountry.setLocalName(updatedCountry.getLocalName());
                existingCountry.setGovernmentForm(updatedCountry.getGovernmentForm());
                existingCountry.setHeadOfState(updatedCountry.getHeadOfState());
                existingCountry.setCapital(updatedCountry.getCapital());
                existingCountry.setCode2(updatedCountry.getCode2());

                System.out.println("Saving updated changes");
                return countryRepository.save(existingCountry);
            } catch (Exception e) {
                System.out.println("Inside catch statement");
                throw new CountryUpdateBadRequestException(code);
            }
        } else {
            throw new CountryNotFoundException(code);
        }
    }

    public boolean deleteCountry(String code) {
        Optional<CountryEntity> countryOptional = countryRepository.findById(code);
        if (countryOptional.isPresent()) {
            countryRepository.delete(countryOptional.get());
            return true;
        } else {
            return false;
        }
    }
}