package com.coolpotato.fun_projects.chess_application.models;

import java.util.Objects;

public class Direction {
    int x;
    int y;
    private static final double errorMargin = 0.001;

    public Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction other = (Direction) o;

        double xRelation = (double) this.x / other.x;
        double yRelation = (double) this.y / other.y;


        return xRelation / yRelation > 1 - errorMargin && xRelation / yRelation < 1 + errorMargin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
