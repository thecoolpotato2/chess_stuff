package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color, Coordinate currentLocation) {
        super(color, currentLocation);
        this.possibleDeltaCoordinates = initializePossibleDeltaCoordinates();
    }

    @Override
    public int getValue() {
        return Integer.MAX_VALUE;
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    private static ArrayList<Coordinate> initializePossibleDeltaCoordinates() {
        ArrayList<Coordinate> possibleDeltaCoordinates = new ArrayList<>();

            possibleDeltaCoordinates.add(new Coordinate(-1, -1));
            possibleDeltaCoordinates.add(new Coordinate(-1, 1));
            possibleDeltaCoordinates.add(new Coordinate(1, 1));
            possibleDeltaCoordinates.add(new Coordinate(1, -1));

            possibleDeltaCoordinates.add(new Coordinate(0, 1));
            possibleDeltaCoordinates.add(new Coordinate(0, -1));
            possibleDeltaCoordinates.add(new Coordinate(1, 0));
            possibleDeltaCoordinates.add(new Coordinate(-1, 0));


        return possibleDeltaCoordinates;
    }
}
