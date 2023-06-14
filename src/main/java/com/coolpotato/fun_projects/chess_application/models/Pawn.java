package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private final List<Direction> checkingDirections;

    public Pawn(Color color, Coordinate currentLocation) {
        super(color, currentLocation);
        this.possibleDeltaCoordinates = initializePossibleDeltaCoordinates();
        this.checkingDirections = initializeCheckingDirections();
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

    public List<Direction> getCheckingDirections() {
        return this.color == Color.WHITE ? checkingDirections : checkingDirections.stream()
                .map(c -> new Direction(c.x, -1 * c.y)).toList();
    }

    private static final List<Direction> initializeCheckingDirections() {
        ArrayList<Direction> checkingDirections = new ArrayList<>();
        checkingDirections.add(new Direction(1, 1));
        checkingDirections.add(new Direction(-1, 1));

        return checkingDirections;
    }
}
