package com.wellid.exercise.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
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

    @Override
    public String toString() {
        return String.format("[%d, %d]", this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointEntity point = (PointEntity) o;
        return point.x == x && point.y == y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + x;
        result = prime * result + y;

        return result;
    }
}
