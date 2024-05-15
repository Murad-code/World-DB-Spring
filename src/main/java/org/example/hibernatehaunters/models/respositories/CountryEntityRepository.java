package org.example.hibernatehaunters.models.respositories;

import org.example.hibernatehaunters.models.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryEntityRepository extends JpaRepository<CountryEntity, String> {
}