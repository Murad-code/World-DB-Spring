package org.example.hibernatehaunters.service;

import org.example.hibernatehaunters.models.entities.CountryLanguageEntity;
import org.example.hibernatehaunters.models.entities.CountryLanguageIdEntity;
import org.example.hibernatehaunters.models.exceptions.countrylanguage.CountryLanguageBadRequestException;
import org.example.hibernatehaunters.models.exceptions.countrylanguage.CountryLanguageNotFoundException;
import org.example.hibernatehaunters.models.respositories.CountryLanguageEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryLanguageService {

    private final CountryLanguageEntityRepository countrylanguageEntityRepository;

    @Autowired
    public CountryLanguageService(CountryLanguageEntityRepository countrylanguageEntityRepository) {
        this.countrylanguageEntityRepository = countrylanguageEntityRepository;
    }

    public CountryLanguageEntity createCountryLanguage(CountryLanguageEntity countryLanguage) throws CountryLanguageBadRequestException {
        if (countryLanguage.getId() == null) {
            throw new CountryLanguageBadRequestException("\n countryLanguage missing ID" + countryLanguage.toString());
        }
        if (countryLanguage.getCountryCode() == null){
            throw new CountryLanguageBadRequestException("\n countryLanguage missing countryCode" + countryLanguage.toString());
        }
        return countrylanguageEntityRepository.save(countryLanguage);
    }

    public List<CountryLanguageEntity> getAllCountryLanguages() {
        return countrylanguageEntityRepository.findAll();
    }

    public List<CountryLanguageEntity> getCountryLanguageByCountryCode(String countryCode) {
        return countrylanguageEntityRepository.findAllCountryLanguageById_CountryCode(countryCode);

    }

    public List<CountryLanguageEntity> getCountryLanguageByLanguage(String language){
        return countrylanguageEntityRepository.findAllCountryLanguageById_Language(language);
    }

    public CountryLanguageEntity getCountryLanguageById(CountryLanguageIdEntity id) throws CountryLanguageNotFoundException {
        if(countrylanguageEntityRepository.findCountryLanguageEntityById(id).isPresent()){
            throw new CountryLanguageNotFoundException(id.toString());
        } else{
            return countrylanguageEntityRepository.findCountryLanguageEntityById(id).get();
        }
    }

    public CountryLanguageEntity updateCountryLanguage(CountryLanguageIdEntity id, CountryLanguageEntity updatedCountryLanguage) {
        Optional<CountryLanguageEntity> countryLanguageOptional = countrylanguageEntityRepository.findById(id);
        if (countryLanguageOptional.isPresent()) {
            CountryLanguageEntity existingCountryLanguage = countryLanguageOptional.get();
            //existingCountryLanguage.setCountryCode(updatedCountryLanguage.getCountryCode());
            existingCountryLanguage.setIsOfficial(updatedCountryLanguage.getIsOfficial());
            existingCountryLanguage.setPercentage(updatedCountryLanguage.getPercentage());

            return countrylanguageEntityRepository.save(existingCountryLanguage);
        } else {
            return null;
        }
    }

    public boolean deleteCountryLanguage(CountryLanguageIdEntity id) {
        Optional<CountryLanguageEntity> countryLanguageOptional = countrylanguageEntityRepository.findById(id);
        if (countryLanguageOptional.isPresent()) {
            countrylanguageEntityRepository.delete(countryLanguageOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void deleteCountryLanguageEntitiesByCountryCode(String countryCode) {
        countrylanguageEntityRepository.deleteCountryLanguageEntityByCountryCode_Code(countryCode);
    }
}