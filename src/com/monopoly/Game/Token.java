package com.monopoly.Game;

import com.monopoly.Game.Squares.Square;

public class Token {
    private Square location;

    public Token(Square location) {
        this.location = location;
    }

    public Square getLocation() {
        return location;
    }

    public void setLocation(Square location) {
        this.location = location;
    }
}
