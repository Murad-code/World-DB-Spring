package org.example.hibernatehaunters.models.respositories;

import org.example.hibernatehaunters.models.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityEntityRepository extends JpaRepository<CityEntity, Integer> {
    void deleteCityEntitiesByCountryCode_Code(String Code);
}