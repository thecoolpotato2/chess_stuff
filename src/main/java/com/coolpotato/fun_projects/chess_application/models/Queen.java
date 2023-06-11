package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Queen implements Piece{
    private boolean dead;
    private ArrayList<Coordinate> possibleDeltaCoordinates;

    public Queen() {
        this.dead = false;
        this.possibleDeltaCoordinates = initializePossibleDeltaCoordinates();
    }
    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public int getValue() {
        return 9;
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
        return PieceType.QUEEN;
    }

    private static ArrayList<Coordinate> initializePossibleDeltaCoordinates() {
        ArrayList<Coordinate> possibleDeltaCoordinates = new ArrayList<>();

        for(int i = 1; i < 8; i++) {
            possibleDeltaCoordinates.add(new Coordinate(-i, -i));
            possibleDeltaCoordinates.add(new Coordinate(-i, i));
            possibleDeltaCoordinates.add(new Coordinate(i, i));
            possibleDeltaCoordinates.add(new Coordinate(i, -i));
        }

        for(int i = 1; i < 8; i++) {
            possibleDeltaCoordinates.add(new Coordinate(0, i));
            possibleDeltaCoordinates.add(new Coordinate(0, -i));
            possibleDeltaCoordinates.add(new Coordinate(i, 0));
            possibleDeltaCoordinates.add(new Coordinate(-i, 0));
        }


        return possibleDeltaCoordinates;
    }
}
