package com.coolpotato.fun_projects.chess_application.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Board {
    private HashMap<Coordinate, Piece> currentPieces;

    private Coordinate whiteKingPlacement;
    private Coordinate blackKingPlacement;

    public Board() {
        this.currentPieces = initializeBoard();
        this.whiteKingPlacement = new Coordinate(0, 4);
        this.blackKingPlacement = new Coordinate(7, 4);
    }

    private static HashMap<Coordinate, Piece> initializeBoard() {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();

        //place pawns
        for(int i = 0; i < 8; i++) {
            pieces.put(new Coordinate(i, 1), new Pawn(Color.WHITE, new Coordinate(i, 1)));
            pieces.put(new Coordinate(i, 6), new Pawn(Color.BLACK, new Coordinate(i, 6)));
        }

        //place rooks
        pieces.put(new Coordinate(0, 0), new Rook(Color.WHITE, new Coordinate(0, 0)));
        pieces.put(new Coordinate(7, 0), new Rook(Color.WHITE, new Coordinate(7, 0)));
        pieces.put(new Coordinate(0, 7), new Rook(Color.BLACK, new Coordinate(0, 7)));
        pieces.put(new Coordinate(7, 7), new Rook(Color.BLACK, new Coordinate(7, 7)));

        //place knights
        pieces.put(new Coordinate(1, 0), new Knight(Color.WHITE, new Coordinate(1, 0)));
        pieces.put(new Coordinate(6, 0), new Knight(Color.WHITE, new Coordinate(6, 0)));
        pieces.put(new Coordinate(1, 7), new Knight(Color.BLACK, new Coordinate(1, 7)));
        pieces.put(new Coordinate(6, 7), new Knight(Color.BLACK, new Coordinate(6, 7)));

        //place bishops
        pieces.put(new Coordinate(2, 0), new Bishop(Color.WHITE, new Coordinate(2, 0)));
        pieces.put(new Coordinate(5, 0), new Bishop(Color.WHITE, new Coordinate(5, 0)));
        pieces.put(new Coordinate(2, 7), new Bishop(Color.BLACK, new Coordinate(2, 7)));
        pieces.put(new Coordinate(5, 7), new Bishop(Color.BLACK, new Coordinate(5, 7)));

        //place queens
        pieces.put(new Coordinate(3, 0), new Queen(Color.WHITE, new Coordinate(3, 0)));
        pieces.put(new Coordinate(3, 7), new Queen(Color.BLACK, new Coordinate(3, 7)));

        //place kings
        pieces.put(new Coordinate(4, 0), new King(Color.WHITE, new Coordinate(4, 0)));
        pieces.put(new Coordinate(4, 7), new King(Color.BLACK, new Coordinate(4, 7)));


        return pieces;
    }

    public List<Coordinate> getValidPotentialMoves(Coordinate currentCoordinate) {
        ArrayList<Coordinate> validPlacements = new ArrayList<>();

        Piece piece = this.currentPieces.get(currentCoordinate);

        if (piece == null) {
            return validPlacements;
        }

        List<Coordinate> potentialMoves = piece.getPossibleDeltaCoordinates();

        potentialMoves = filterOutOfBoundsMoves(potentialMoves, currentCoordinate);

        //Knights are not blocked by anything
        if(piece.getType() != PieceType.KNIGHT) {
            potentialMoves = filterBlockedMoves(potentialMoves, currentCoordinate);
        }
        filterMovesCausingCheck(potentialMoves, currentCoordinate);

        //Handle special pawn moves
        //En passant not currently supported
        potentialMoves = removeInvalidPawnMoves(potentialMoves, piece);

        return potentialMoves;
    }

    private List<Coordinate>  filterOutOfBoundsMoves(List<Coordinate> potentialMoves, Coordinate currentLocation) {
        return potentialMoves.stream()
                .map(move -> move.add(currentLocation))
                .filter(coordinate -> !(isOutOfBounds(coordinate)))
                .map(coordinate -> coordinate.subtract(currentLocation))
                .collect(Collectors.toList());
    }

    private List<Coordinate> filterBlockedMoves(List<Coordinate> potentialMoves, Coordinate currentLocation) {
        ArrayList<Coordinate> potentialSquares = (ArrayList<Coordinate>) potentialMoves.stream().map(p -> p.add(currentLocation)).toList();
        List<Coordinate> blockingSquares = getBlockingSquares(potentialMoves, currentLocation);

        Color currentColor = currentPieces.get(currentLocation).getColor();

        blockingSquares
                .forEach(
                        blockingSquare -> {
                            Direction direction = currentLocation.getDirectionToOtherCoordinate(blockingSquare);
                            potentialSquares.removeAll(potentialSquares.stream().filter(
                                    p -> blockingSquare.getDirectionToOtherCoordinate(p).equals(direction)
                            ).toList());
                        }
                );

        //Also remove the blocking squares themselves, if they are of the same color
        potentialSquares.removeAll(blockingSquares.stream().filter(p -> {
                Piece piece = currentPieces.get(p);
                return piece.getColor() == currentColor;
        }).toList());

        return potentialSquares.stream().map(p -> p.subtract(currentLocation)).toList();
    }

    private List<Coordinate> getBlockingSquares(List<Coordinate> potentialMoves, Coordinate currentLocation) {
        return potentialMoves.stream()
                .map(move -> move.add(currentLocation))
                .filter(coordinate -> this.currentPieces.get(coordinate) != null)
                .collect(Collectors.toList());
    }

    //This method assumes that out of bounds and blocked moves have already been filtered
    private void filterMovesCausingCheck(List<Coordinate> potentialMoves, Coordinate currentLocation) {
        HashMap<Coordinate, Piece> pieces = new HashMap<>();
        pieces.putAll(this.currentPieces);

         Piece currentPiece = pieces.get(currentLocation);

         List<Coordinate> movesToBeFiltered = new ArrayList<>();

         for(Coordinate potentialMove : potentialMoves) {
             Coordinate newLocation = currentLocation.add(potentialMove);
             pieces.remove(currentLocation);
             pieces.put(newLocation, currentPiece);
             boolean causesCheck = checkForCheck(pieces, currentPiece.getColor());

             if(causesCheck) {
                 movesToBeFiltered.add(potentialMove);
             }

             pieces.remove(newLocation);
             pieces.put(currentPiece.getCurrentLocation(), currentPiece);
         }

         potentialMoves.removeAll(movesToBeFiltered);
    }

    //hehe
    //Also this should probably be refactored
    private boolean checkForCheck(HashMap<Coordinate, Piece> pieces, Color color) {
        Coordinate kingLocation =
                pieces.entrySet().stream().filter(
                        e -> e.getValue().getType() == PieceType.KING && e.getValue().getColor() == color
                ).map(e -> e.getKey())
                        .findFirst().orElseThrow(() -> new IllegalArgumentException("No king of that color in provided map"));
        
        List<Piece> nearestPieces =
                getAllPossibleDirections().
                        stream()
                        .map(direction -> getNearestPieceForDirection(pieces, kingLocation, direction))
                        .toList();

        Optional<Piece> checkingPiece = nearestPieces.stream()
                .filter(piece -> piece.getColor() != color)
                .filter(piece -> piece.getType() != PieceType.PAWN && piece.getType() != PieceType.KNIGHT)
                .filter(piece -> {
                    Direction direction = piece.getCurrentLocation().getDirectionToOtherCoordinate(kingLocation);
                    return piece.isPossibleMove(direction);
                }).findAny();

        if(checkingPiece.isPresent()) {
            return true;
        }

        //handle bitch ass pawns
        boolean pawnCheck = nearestPieces.stream()
                .filter(piece -> piece.getType() == PieceType.PAWN && piece.getColor() != color)
                .map(p -> (Pawn) p)
                .anyMatch(p -> {
                    List<Direction> checkingDirections = p.getAttackingDirections();
                    return checkingDirections.stream()
                            .anyMatch(d -> p.getCurrentLocation().addDirection(d).equals(kingLocation));
                });

        if(pawnCheck) {
            return true;
        }

        //Handle knights
        List<Knight> opposingKnights = pieces.values().stream()
                .filter(piece -> piece.getType() == PieceType.KNIGHT && piece.getColor() != color)
                .map(p -> (Knight) p).toList();

        return opposingKnights.stream()
                .anyMatch(knight -> {
                    Direction direction = knight.getCurrentLocation().getDirectionToOtherCoordinate(kingLocation);
                    return knight.isPossibleMove(direction);
                });
    }

    private Piece getNearestPieceForDirection(HashMap<Coordinate, Piece> pieces, Coordinate currentLocation, Direction direction) {

        while(true) {
            Coordinate newLocation = currentLocation.addDirection(direction);
            if(isOutOfBounds(newLocation)) {
                return null;
            }

            Piece piece = pieces.get(newLocation);
            if(piece != null) {
                return piece;
            }
        }
    }

    private boolean isOutOfBounds(Coordinate coordinate) {
        return coordinate.getX() > 7 || coordinate.getX() < 0 || coordinate.getY() > 7 || coordinate.getY() < 0;
    }
    
    private static List<Direction> getAllPossibleDirections() {
        ArrayList<Direction> directions = new ArrayList<>();
        
            directions.add(new Direction(-1, -1));
            directions.add(new Direction(-1, 1));
            directions.add(new Direction(1, 1));
            directions.add(new Direction(1, -1));
            directions.add(new Direction(0, 1));
            directions.add(new Direction(0, -1));
            directions.add(new Direction(1, 0));
            directions.add(new Direction(-1, 0));

            return directions;
    }

    private List<Coordinate> removeInvalidPawnMoves(List<Coordinate> potentialMoves, Piece piece) {
        if(piece.getType() != PieceType.PAWN) {
            return potentialMoves;
        }

        Pawn pawn = (Pawn) piece;

        List<Coordinate> movesAllowedToStay = new ArrayList<>();

        //check attacking moves are allowed
        //note - these might already be removed from potential moves, but if not, we allow them to stay based on the logic below
        movesAllowedToStay.addAll(pawn.getAttackingDirections()
                .stream().map(d -> pawn.getCurrentLocation().addDirection(d))
                .filter(coordinate -> currentPieces.get(coordinate) != null
                        && currentPieces.get(coordinate).getColor() != pawn.getColor())
                .toList());

        if(currentPieces.get(pawn.getCurrentLocation().add(new Coordinate(0, 1))) == null) {
            movesAllowedToStay.add(new Coordinate(0, 1));
        }

        if(pawn.isAtStartingPosition() && currentPieces.get(pawn.getCurrentLocation().add(new Coordinate(0, 2))) == null) {
            movesAllowedToStay.add(new Coordinate(0, 2));
        }

        return potentialMoves.stream().filter(movesAllowedToStay::contains).toList();
    }
}
