package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color, Coordinate currentLocation) {
        super(color, currentLocation);
        this.possibleDeltaCoordinates = initializePossibleDeltaCoordinates();
    }

    @Override
    public int getValue() {
        return 5;
    }

    private static ArrayList<Coordinate> initializePossibleDeltaCoordinates() {
        ArrayList<Coordinate> deltaCoordinates = new ArrayList<>();

        for(int i = 1; i < 8; i++) {
            deltaCoordinates.add(new Coordinate(0, i));
            deltaCoordinates.add(new Coordinate(0, -i));
            deltaCoordinates.add(new Coordinate(i, 0));
            deltaCoordinates.add(new Coordinate(-i, 0));
        }

        return deltaCoordinates;
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }

}
