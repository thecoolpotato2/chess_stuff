package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private final List<Direction> checkingDirections;
    private final List<Direction> passiveDirections;

    public Pawn(Color color, Coordinate currentLocation) {
        super(color, currentLocation);
        this.possibleDeltaCoordinates = initializePossibleDeltaCoordinates();
        this.checkingDirections = initializeCheckingDirections();
        this.passiveDirections = initializePassiveDirections();
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

    public List<Direction> getAttackingDirections() {
        return this.color == Color.WHITE ? checkingDirections : checkingDirections.stream()
                .map(c -> new Direction(c.x, -1 * c.y)).toList();
    }

    public List<Direction> getPassiveDirections() {
        return this.color == Color.WHITE ? passiveDirections :
        passiveDirections.stream()
                .map(c -> new Direction(c.x, -1 * c.y)).toList();
    }

    public boolean isAtStartingPosition() {
        return (this.getCurrentLocation().getY() == 1 && this.color == Color.WHITE) ||
                (this.getCurrentLocation().getY() == 6 && this.color == Color.BLACK);
    }

    private static List<Direction> initializeCheckingDirections() {
        ArrayList<Direction> checkingDirections = new ArrayList<>();
        checkingDirections.add(new Direction(1, 1));
        checkingDirections.add(new Direction(-1, 1));

        return checkingDirections;
    }

    private static List<Direction> initializePassiveDirections() {
        ArrayList<Direction> passiveDirections = new ArrayList<>();

        passiveDirections.add(new Direction(0, 1));
        passiveDirections.add(new Direction(0, 2));

        return passiveDirections;
    }
}
