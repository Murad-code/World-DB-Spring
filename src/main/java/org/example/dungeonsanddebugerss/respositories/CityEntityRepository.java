package org.example.dungeonsanddebugerss.respositories;

import org.example.dungeonsanddebugerss.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityEntityRepository extends JpaRepository<CityEntity, Integer> {
}