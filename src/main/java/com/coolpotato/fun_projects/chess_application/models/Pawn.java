package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    private boolean dead;
    private Color color;
    public Pawn(Color color) {
        this.dead = false;
        this.color = color;
    }
    @Override
    public boolean isDead() {
        return this.dead;
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public List<Coordinate> getPossibleDeltaCoordinates() {
        ArrayList<Coordinate> deltaCoordinates = new ArrayList<>();

        deltaCoordinates.add(new Coordinate(0, 1));
        deltaCoordinates.add(new Coordinate(0, 2));
        deltaCoordinates.add(new Coordinate(1, 1));
        deltaCoordinates.add(new Coordinate(-1, 1));

        return deltaCoordinates;
    }

    @Override
    public void kill() {
        this.dead = true;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
