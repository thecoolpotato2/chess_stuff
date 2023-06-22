package com.coolpotato.fun_projects.chess_application.models;

import lombok.Data;

import java.util.Objects;

public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate add(Coordinate coordinate) {
        return new Coordinate(this.x + coordinate.x, this.y + coordinate.y);
    }

    public Coordinate subtract(Coordinate coordinate) {
        return new Coordinate(this.x - coordinate.x, this.y - coordinate.y);
    }

    //This method returns a "coordinate" that can be considered a vector pointing from this coordinate to the other coordinate
    //The vector is always "normalized" with respect to the dimension with the least movement
    public Direction getDirectionToOtherCoordinate(Coordinate other) {
        return new Direction(other.x - this.x, other.y - this.y);
    }

    public Coordinate addDirection(Direction direction) {
        return new Coordinate(this.x + direction.x, this.y + direction.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
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


    @Override
    public String toString() {
        return "{x:" + this.x + ",y:" + this.y + "}";
    }
}
