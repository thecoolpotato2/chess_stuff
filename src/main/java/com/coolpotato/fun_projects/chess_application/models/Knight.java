package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Piece {
    private boolean dead;
    private Color color;
    private ArrayList<Coordinate> possibleDeltaCoordinates;

    public Knight(Color color) {
        this.color = color;
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
        return this.possibleDeltaCoordinates;
    }

    @Override
    public void kill() {
        this.dead = true;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    @Override
    public Color getColor() {
        return this.color;
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
