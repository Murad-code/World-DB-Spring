package org.example.hibernatehaunters.service;

import org.example.hibernatehaunters.models.entities.CountryEntity;
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

    public CountryEntity updateCountry(String code, CountryEntity updatedCountry) throws CountryUpdateBadRequestException {
        Optional<CountryEntity> countryOptional = countryRepository.findById(code);
        if (countryOptional.isPresent()) {
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

                return countryRepository.save(existingCountry);
            } catch (Exception e) {
                throw new CountryUpdateBadRequestException(code);
            }
        } else {
            return null;
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