package com.coolpotato.fun_projects.chess_application.controllers;

import com.coolpotato.fun_projects.chess_application.models.Board;
import com.coolpotato.fun_projects.chess_application.models.Coordinate;
import com.coolpotato.fun_projects.chess_application.models.Piece;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chess")
public class BoardController {
    private Board board;

    public BoardController(Board board) {
        this.board = board;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getBoard")
    public Map<Coordinate, Piece> getBoardPieces() {
        return this.board.getCurrentPieces();
    }
}
