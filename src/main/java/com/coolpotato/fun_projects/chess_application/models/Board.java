package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private HashMap<Coordinate, Piece> pieces;
    public Board() {
        this.pieces = initializeBoard();
    }

    private static HashMap<Coordinate, Piece> initializeBoard() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();

        //place pawns
        for(int i = 0; i < 8; i++) {
            pieces.put(new Coordinate(i, 1), new Pawn(Color.WHITE));
            pieces.put(new Coordinate(i, 6), new Pawn(Color.BLACK));
        }

        //place rooks
        pieces.put(new Coordinate(0,0), new Rook(Color.WHITE));
        pieces.put(new Coordinate(7, 0), new Rook(Color.WHITE));
        pieces.put(new Coordinate(0, 7), new Rook(Color.BLACK));
        pieces.put(new Coordinate(7, 7), new Rook(Color.BLACK));

        //place knights
        pieces.put(new Coordinate(1,0), new Knight(Color.WHITE));
        pieces.put(new Coordinate(6, 0), new Knight(Color.WHITE));
        pieces.put(new Coordinate(1, 7), new Knight(Color.BLACK));
        pieces.put(new Coordinate(6, 7), new Knight(Color.BLACK));

        //place bishops
        pieces.put(new Coordinate(2,0), new Bishop(Color.WHITE));
        pieces.put(new Coordinate(5, 0), new Bishop(Color.WHITE));
        pieces.put(new Coordinate(2, 7), new Bishop(Color.BLACK));
        pieces.put(new Coordinate(5, 7), new Bishop(Color.BLACK));

        //place queens
        pieces.put(new Coordinate(3, 0), new Queen(Color.WHITE));
        pieces.put(new Coordinate(3, 7), new Queen(Color.BLACK));

        //place kings
        pieces.put(new Coordinate(4, 0), new King(Color.WHITE));
        pieces.put(new Coordinate(4, 7), new King(Color.BLACK));

        return pieces;
    }

    public List<Coordinate> getValidPlacements(Coordinate currentCoordinate) {
        ArrayList<Coordinate> validPlacements = new ArrayList<>();

        Piece piece = this.pieces.get(currentCoordinate);

        if (piece == null) {
            return validPlacements;
        }

        List<Coordinate> potentialMoves = piece.getPossibleDeltaCoordinates();

        potentialMoves = filterOutOfBoundsMoves(potentialMoves, currentCoordinate);
        potentialMoves = filterBlockedMoves(potentialMoves, currentCoordinate);

    }

    private List<Coordinate>  filterOutOfBoundsMoves(List<Coordinate> potentialMoves, Coordinate currentLocation) {
        return potentialMoves.stream()
                .map(move -> move.add(currentLocation))
                .filter(coordinate -> !(coordinate.getX() > 7 || coordinate.getX() < 0 || coordinate.getY() > 7 || coordinate.getY() < 0))
                .map(coordinate -> coordinate.subtract(currentLocation))
                .collect(Collectors.toList());
    }

    private List<Coordinate> filterBlockedMoves(List<Coordinate> potentialMoves, Coordinate currentLocation) {
        ArrayList<Coordinate> potentialSquares = (ArrayList<Coordinate>) potentialMoves.stream().map(p -> p.add(currentLocation)).toList();
        List<Coordinate> blockingSquares = getBlockingSquares(potentialMoves, currentLocation);

        blockingSquares
                .forEach(
                        blockingSquare -> {
                            Direction direction = currentLocation.getDirectionToOtherCoordinate(blockingSquare);
                            potentialSquares.removeAll(potentialSquares.stream().filter(
                                    p -> blockingSquare.getDirectionToOtherCoordinate(p).equals(direction)
                            ).toList());
                        }
                );
        return potentialSquares.stream().map(p -> p.subtract(currentLocation)).toList();
    }

    private List<Coordinate> getBlockingSquares(List<Coordinate> potentialMoves, Coordinate currentLocation) {
        return potentialMoves.stream()
                .map(move -> move.add(currentLocation))
                .filter(coordinate -> this.pieces.get(coordinate) != null)
                .collect(Collectors.toList());
    }
}
