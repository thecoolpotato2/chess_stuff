package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color, Coordinate currentLocation) {
        super(color, currentLocation);
        this.possibleDeltaCoordinates = initializePossibleDeltaCoordinates();
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    //This could probably be formularized
    private static ArrayList<Coordinate> initializePossibleDeltaCoordinates() {
        ArrayList<Coordinate> possibleDeltaCoordinates = new ArrayList<>();

        possibleDeltaCoordinates.add(new Coordinate(-1, 2));
        possibleDeltaCoordinates.add(new Coordinate(1, 2));
        possibleDeltaCoordinates.add(new Coordinate(2, 1));
        possibleDeltaCoordinates.add(new Coordinate(2, -1));
        possibleDeltaCoordinates.add(new Coordinate(1, -2));
        possibleDeltaCoordinates.add(new Coordinate(-1, -2));
        possibleDeltaCoordinates.add(new Coordinate(-2, -1));
        possibleDeltaCoordinates.add(new Coordinate(-2, 1));

        return possibleDeltaCoordinates;
    }

}
