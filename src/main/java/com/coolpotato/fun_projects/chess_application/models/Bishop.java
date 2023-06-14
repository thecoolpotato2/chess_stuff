package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {


    public Bishop(Color color, Coordinate currentLocation) {
        super(color, currentLocation);
        super.possibleDeltaCoordinates = initializePossibleDeltaCoordinates();
    }

    @Override
    public int getValue() {
        return 3;
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
