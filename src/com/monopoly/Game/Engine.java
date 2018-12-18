package com.monopoly.Game;
import com.monopoly.Game.Squares.PropertySquare;
import com.monopoly.Game.Squares.Square;

public class Engine {
    private Square[][] squares;//actual game board
    private Game game;
    private Board board = new Board(game);

    public Engine () {
        squares = new Square[11][11];
        initializeBoard();
    }

    public void initializeBoard() {
        squares[0][0] = new PropertySquare("Free Parking",0,game,board);

    }

}
