package org.example.hibernatehaunters.models.respositories;

import org.example.hibernatehaunters.models.entities.CountryLanguageEntity;
import org.example.hibernatehaunters.models.entities.CountryLanguageIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryLanguageEntityRepository extends JpaRepository<CountryLanguageEntity, CountryLanguageIdEntity> {

    List<CountryLanguageEntity> findAllCountryLanguageById_CountryCode(String countryCode);
    List<CountryLanguageEntity> findAllCountryLanguageById_Language(String language);
    CountryLanguageEntity findCountryLanguageEntityById(CountryLanguageIdEntity id);
}