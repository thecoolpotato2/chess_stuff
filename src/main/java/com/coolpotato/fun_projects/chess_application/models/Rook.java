package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.List;

public class Rook implements Piece {
    private boolean dead;

    public Rook() {
        this.dead = false;
    }
    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public List<Coordinate> getPossibleDeltaCoordinates() {
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
    public void kill() {
        this.dead = true;
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }


}
