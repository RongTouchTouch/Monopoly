package com.monopoly.Game.Squares;

import com.monopoly.Game.Player;

import java.util.List;

public class VirtualRollDiceSquare extends Square {
    private final int maxStraightTurns = 3;

    public VirtualRollDiceSquare() {
        super(null, null, null);
    }

    public void passBy(Player player) {
        return;
    }

    public boolean landOn(Player player) {
        List<Integer> diceValueHistory = player.getDiceValueHistory();
        if (diceValueHistory.size() >= 3) {
            int size = diceValueHistory.size();
            List<Integer> historyTail = diceValueHistory.subList(size - maxStraightTurns, size - 2);
            int lastDiceValue = diceValueHistory.get(size - 1);
            boolean goToJail = true;
            for (Integer j : historyTail) {
                if (j != lastDiceValue) {
                    goToJail = false;
                    break;
                }
            }
            if (goToJail) {
                player.setInJail(true);
                System.out.println(player.getName() + "，你连续三次掷得 " + lastDiceValue + "，进入监狱！");
                return false;
            }
        }
        return true;
    }

    public void removePlayer(Player player) {
        return;
    }
}
