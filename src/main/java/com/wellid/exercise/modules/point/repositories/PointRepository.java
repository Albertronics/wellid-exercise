package com.wellid.exercise.modules.point.repositories;

import com.wellid.exercise.modules.point.entities.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointEntity, PointEntity.PointEntityId> {
}
