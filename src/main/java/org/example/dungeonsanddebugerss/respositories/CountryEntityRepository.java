package org.example.dungeonsanddebugerss.respositories;

import org.example.dungeonsanddebugerss.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryEntityRepository extends JpaRepository<CountryEntity, String> {
}