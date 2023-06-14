package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color, Coordinate currentLocation) {
        super(color, currentLocation);
        this.possibleDeltaCoordinates = initializePossibleDeltaCoordinates();
    }

    @Override
    public int getValue() {
        return 1;
    }

    private static ArrayList<Coordinate> initializePossibleDeltaCoordinates() {
        ArrayList<Coordinate> deltaCoordinates = new ArrayList<>();

        deltaCoordinates.add(new Coordinate(0, 1));
        deltaCoordinates.add(new Coordinate(0, 2));
        deltaCoordinates.add(new Coordinate(1, 1));
        deltaCoordinates.add(new Coordinate(-1, 1));

        return deltaCoordinates;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public List<Coordinate> getPossibleDeltaCoordinates() {

        if(this.color == Color.BLACK) {
            return this.possibleDeltaCoordinates.stream()
                    .map(coordinate -> new Coordinate(coordinate.x, coordinate.y *= -1)).toList();
        }

        return this.possibleDeltaCoordinates;
    }
}
