package org.example.hibernatehaunters.models.respositories;

import org.example.hibernatehaunters.models.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityEntityRepository extends JpaRepository<CityEntity, Integer> {
}