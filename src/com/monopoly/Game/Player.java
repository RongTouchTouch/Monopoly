package com.monopoly.Game;

import com.monopoly.Game.Squares.Square;
import com.monopoly.Game.Squares.VirtualRollDiceSquare;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int step;
    private Game game;
    private Board board;
    private Token token;
    private double cash;
    private boolean isInJail;
    private List<Integer> diceValueHistory = new ArrayList<>();

    public Player(String name, Game game, Board board) {
        this.name = name;
        this.game = game;
        this.board = board;
        this.cash = 1500;
        this.isInJail = false;
        token = new Token(board.getGoSquare());
    }

    public boolean isInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }

    public boolean isMovable() {
        return !isInJail();
    }

    public List<Integer> getDiceValueHistory() {
        return diceValueHistory;
    }

    public void exitGame() {
        game.removePlayer(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCash() {
        return cash;
    }

    public int roll() {
        int faceValue = Cup.getInstance().roll();
        diceValueHistory.add(faceValue);
        step = faceValue;
        return faceValue;
    }

    public void takeTurn() {
        System.out.println(name + "，轮到你了！你拥有 " + cash + " 金钱。");
        System.out.print("你可以 (1) 掷骰子、(2) 退出游戏。你的选择是 ");
        int option = Game.readOption(2);
        switch (option) {
            case 1:
                int faceValue = roll();
                System.out.println(name + "掷出点数 " + faceValue + "。");
                Square rollDiceSquare = new VirtualRollDiceSquare();
                if (!rollDiceSquare.landOn(this)) {
                    break;
                }
                Square newLocation = board.getSquare(token.getLocation(), faceValue);
                token.setLocation(newLocation);
                newLocation.landOn(this);
                break;
            case 2:
                game.removePlayer(this);
                break;
        }
    }

    public void addCash(double amount) {
        cash += amount;
    }

    public boolean reduceCash(double amount) {
        if (cash - amount >= 0) {
            cash -= amount;
            return true;
        } else {
            return false;
        }
    }

    public int getStep()
    {
        return step;
    }
}
