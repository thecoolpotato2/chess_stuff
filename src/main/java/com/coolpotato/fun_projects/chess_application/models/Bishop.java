package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements Piece {
    private boolean dead;
    private ArrayList<Coordinate> possibleDeltaCoordinates;

    public Bishop() {
        this.dead = false;
        this.possibleDeltaCoordinates = initializePossibleDeltaCoordinates();
    }
    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public List<Coordinate> getPossibleDeltaCoordinates() {
        return possibleDeltaCoordinates;
    }

    @Override
    public void kill() {
        this.dead = true;
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    private static ArrayList<Coordinate> initializePossibleDeltaCoordinates() {
        ArrayList<Coordinate> possibleDeltaCoordinates = new ArrayList<>();

        for(int i = 1; i < 8; i++) {
            possibleDeltaCoordinates.add(new Coordinate(-i, -i));
            possibleDeltaCoordinates.add(new Coordinate(-i, i));
            possibleDeltaCoordinates.add(new Coordinate(i, i));
            possibleDeltaCoordinates.add(new Coordinate(i, -i));
        }

        return possibleDeltaCoordinates;
    }
}
