package com.wellid.exercise.repositories;

import com.wellid.exercise.entities.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointEntity, PointEntity.PointEntityId> {
}
