package com.coolpotato.fun_projects.chess_application.models;

import java.util.List;

//This class is meant to be inherited by each chess piece
public interface Piece {

    boolean isDead();

    int getValue();

    List<Coordinate> getPossibleDeltaCoordinates();

    void kill();

    PieceType getType();

    Color getColor();
}
