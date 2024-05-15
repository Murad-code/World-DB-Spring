package org.example.hibernatehaunters.models.respositories;

import org.example.hibernatehaunters.models.entities.CountrylanguageEntity;
import org.example.hibernatehaunters.models.entities.CountrylanguageEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountrylanguageEntityRepository extends JpaRepository<CountrylanguageEntity, CountrylanguageEntityId> {

    List<CountrylanguageEntity> findAllCountrylanguageById_CountryCode(String countryCode);
    List<CountrylanguageEntity> findAllCountrylanguageById_Language(String language);
}