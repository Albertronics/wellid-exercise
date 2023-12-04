package com.wellid.exercise.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fraction
{
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public double toDouble() {
        return (double) numerator / denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;
        return fraction.denominator == denominator && fraction.numerator == numerator;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + numerator;
        result = prime * result + denominator;

        return result;
    }
}
