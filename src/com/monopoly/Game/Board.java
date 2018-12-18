package com.monopoly.Game;

import com.monopoly.Game.Squares.GoSquare;
import com.monopoly.Game.Squares.PropertySquare;
import com.monopoly.Game.Squares.Square;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Game game;
    private List<Square> squares;

    public Board(Game game) {
        this.game = game;
        squares = new ArrayList<>();

        addGoSquare();
        addPropertySquare("蒙特利尔", 400);
    }

    public Square getGoSquare() {
        return squares.get(0);
    }

    private void addGoSquare() {
        Square square = new GoSquare("开始", game, this);
        squares.add(0, square);
    }

    private void addPropertySquare(String name, double price) {
        Square square = new PropertySquare(name, price, game, this);
        squares.add(square);
    }

    private void removeSquare(Square square) {
        squares.remove(square);
    }

    public Square getSquare(Square location, int distance) {
        int endIndex = (squares.indexOf(location) + distance) % squares.size();
        return squares.get(endIndex);
    }

    public void removePlayer(Player player) {
        for (Square square : squares) {
            square.removePlayer(player);
        }
    }
}
