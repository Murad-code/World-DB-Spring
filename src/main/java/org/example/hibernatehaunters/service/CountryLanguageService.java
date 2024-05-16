package org.example.hibernatehaunters.service;

import org.example.hibernatehaunters.models.entities.CountryLanguageEntity;
import org.example.hibernatehaunters.models.entities.CountryLanguageIdEntity;
import org.example.hibernatehaunters.models.respositories.CountryLanguageEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryLanguageService {

    private final CountryLanguageEntityRepository countrylanguageEntityRepository;

    @Autowired
    public CountryLanguageService(CountryLanguageEntityRepository countrylanguageEntityRepository) {
        this.countrylanguageEntityRepository = countrylanguageEntityRepository;
    }

    public CountryLanguageEntity createCountryLanguage(CountryLanguageEntity countryLanguage) {
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

    public CountryLanguageEntity getCountryLanguageById(CountryLanguageIdEntity id)
    {
        return countrylanguageEntityRepository.findCountryLanguageEntityById(id);
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
}