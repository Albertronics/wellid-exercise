package com.wellid.exercise.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Line
{
    private Fraction slope;
    private double intercept;

    public Line(Fraction slope, double intercept) {
        this.slope = slope;
        this.intercept = intercept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;

        return Double.compare(line.intercept, intercept) == 0 && Objects.equals(slope, line.slope);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((slope == null) ? 0 : slope.hashCode());
        long interceptBits = Double.doubleToLongBits(intercept);
        result = prime * result + (int) (interceptBits ^ (interceptBits >>> 32));

        return result;
    }
}
