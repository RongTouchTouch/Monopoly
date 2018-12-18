package com.monopoly.Game;

public class Dice {
    private int lower, upper;

    public Dice() {
        this(1, 6);
    }

    public Dice(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public int roll() {
        return (int) (Math.random() * (upper - lower + 1)) + lower;
    }
}
