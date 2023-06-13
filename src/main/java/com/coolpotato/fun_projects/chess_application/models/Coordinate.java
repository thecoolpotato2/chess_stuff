package com.coolpotato.fun_projects.chess_application.models;

import lombok.Data;

@Data
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
}
