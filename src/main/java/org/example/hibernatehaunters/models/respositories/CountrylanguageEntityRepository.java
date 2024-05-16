package org.example.hibernatehaunters.models.respositories;

import org.example.hibernatehaunters.models.entities.CountrylanguageEntity;
import org.example.hibernatehaunters.models.entities.CountrylanguageEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountrylanguageEntityRepository extends JpaRepository<CountrylanguageEntity, CountrylanguageEntityId> {
    void deleteCountrylanguageEntityByCountryCode_Code(String countryCode);
}