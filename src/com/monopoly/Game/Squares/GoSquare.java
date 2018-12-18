package com.monopoly.Game.Squares;

import com.monopoly.Game.Board;
import com.monopoly.Game.Game;
import com.monopoly.Game.Player;

public class GoSquare extends Square {
    private double cash = 200;

    public GoSquare(String name, Game game, Board board) {
        super(name, game, board);
    }

    public void passBy(Player player) {
        player.addCash(cash);
        System.out.println(player.getName() + "经过" + name + "，增加 " + cash + " 金钱。");
    }

    public boolean landOn(Player player) {
        player.addCash(cash);
        System.out.println(player.getName() + "到达" + name + "，增加 " + cash + " 金钱。");
        return true;
    }

    public void removePlayer(Player player) {
        return;
    }
}
