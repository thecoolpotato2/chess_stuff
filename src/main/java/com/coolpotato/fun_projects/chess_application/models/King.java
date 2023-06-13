package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class King implements Piece {

    private boolean dead;
    private Color color;
    private ArrayList<Coordinate> possibleDeltaCoordinates;

    public King(Color color) {
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
        return Integer.MAX_VALUE;
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
        return PieceType.KING;
    }

    @Override
    public Color getColor() {
        return this.color;
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
