package com.coolpotato.fun_projects.chess_application.models;

import lombok.Data;

import java.util.Objects;

@Data
public class Direction {
    double x;
    double y;
    private static final double errorMargin = 0.001;

    public Direction(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction other = (Direction) o;

        double xRelation = this.x / other.x;
        double yRelation = this.y / other.y;


        return xRelation / yRelation > 1 - errorMargin && xRelation / yRelation < 1 + errorMargin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
