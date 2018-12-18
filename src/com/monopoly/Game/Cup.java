package com.monopoly.Game;

import java.util.ArrayList;
import java.util.List;

public class Cup {
    private static Cup cupInstance = null;
    private List<Dice> dices;
    private int diceCount;
    private int diceValueLowerBound;
    private int diceValueUpperBound;

    public Cup() {
        this(2, 1, 6);
    }

    public Cup(int diceCount, int diceValueLowerBound, int diceValueUpperBound) {
        this.diceCount = diceCount;
        this.diceValueLowerBound = diceValueLowerBound;
        this.diceValueUpperBound = diceValueUpperBound;
        dices = new ArrayList<>();
        for (int i = 0; i < diceCount; i++) {
            Dice dice = new Dice(diceValueLowerBound, diceValueUpperBound);
            dices.add(dice);
        }
    }

    public static Cup getInstance() {
        if (cupInstance == null) {
            cupInstance = new Cup();
        }
        return cupInstance;
    }

    public int roll() {
        int sum = 0;
        for (Dice dice : dices) {
            sum += dice.roll();
        }
        return sum;
    }
}
