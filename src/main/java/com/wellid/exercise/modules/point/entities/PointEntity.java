package com.wellid.exercise.modules.point.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity(name = "points")
@Accessors(chain = true)
@IdClass(PointEntity.PointEntityId.class)
public class PointEntity
{
    @Id
    @Column(nullable = false)
    private Integer x;

    @Id
    @Column(nullable = false)
    private Integer y;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class PointEntityId implements Serializable {
        private Integer x;
        private Integer y;
    }
}
