package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

//This class is meant to be inherited by each chess piece
public abstract class Piece {
    private boolean dead;
    protected Color color;
    protected ArrayList<Coordinate> possibleDeltaCoordinates;

    private Coordinate currentLocation;

    public Piece(Color color, Coordinate currentLocation) {
        this.dead = false;
        this.color = color;
        this.currentLocation = currentLocation;
    }

    public boolean isDead() {
        return this.dead;
    };

    public abstract int getValue();

    public List<Coordinate> getPossibleDeltaCoordinates() {
        return this.possibleDeltaCoordinates;
    }

    void kill() {
        this.dead = true;
    }

    public abstract PieceType getType();

    Color getColor() {
        return this.color;
    };

    Coordinate getCurrentLocation() {
        return this.currentLocation;
    };

    void setCurrentLocation(Coordinate currentLocation) {
        this.currentLocation = currentLocation;
    }

    boolean isPossibleMove(Direction direction) {
        boolean possibleMove = false;
        return this.getPossibleDeltaCoordinates().stream().map(c -> new Direction(c.x, c.y))
                .filter(d -> d.equals(direction)).findAny().isPresent();
    }
}
