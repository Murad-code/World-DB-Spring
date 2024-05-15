package org.example.dungeonsanddebugerss.respositories;

import org.example.dungeonsanddebugerss.entities.CountryLanguageEntity;
import org.example.dungeonsanddebugerss.entities.CountryLanguageEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryLanguageEntityRepository extends JpaRepository<CountryLanguageEntity, CountryLanguageEntityId> {
}