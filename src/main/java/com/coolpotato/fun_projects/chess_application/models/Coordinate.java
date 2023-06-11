package com.coolpotato.fun_projects.chess_application.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
