package com.monopoly.Game.Squares;

import com.monopoly.Game.Board;
import com.monopoly.Game.Game;
import com.monopoly.Game.Player;

public abstract class Square {
    protected String name;
    protected Game game;
    protected Board board;

    public Square(String name, Game game, Board board) {
        this.name = name;
        this.game = game;
        this.board = board;
    }

    public abstract void passBy(Player player);

    public abstract boolean landOn(Player player);

    public abstract void removePlayer(Player player);
}
