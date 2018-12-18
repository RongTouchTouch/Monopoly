package com.monopoly.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private Player currentPlayer = null;

    public Game() {
        this(4);
    }

    public Game(int playerCount) {
        board = new Board(this);
        players = new ArrayList<>();

        addPlayer("小红");
        addPlayer("小李");
        addPlayer("小明");
        addPlayer("小王");
    }

    public static int readOption(int optionCount) {
        return readOption(1, optionCount);
    }

    public static int readOption(int lower, int upper) {
        Scanner scanner = new Scanner(System.in);
        int option;
        while (true) {
            option = scanner.nextInt();
            if (option >= lower && option <= upper) {
                break;
            } else {
                System.out.print("输入的选择不存在，请重新选择：");
            }
        }
        return option;
    }

    public void play() {
        while (!isGameOver()) {
            System.out.println("----------------------------------------------------------------");
            currentPlayer = players.remove(0);
            players.add(currentPlayer);

            if (currentPlayer.isMovable()) {
                currentPlayer.takeTurn();
            } else if (currentPlayer.isInJail()) {
                System.out.println(currentPlayer.getName() + "正在监狱中。");
            }
        }
    }

    public void addPlayer(String name) {
        Player player = new Player(name, this, board);
        players.add(player);
        System.out.println(name + "加入了游戏。");
    }

    public void removePlayer(Player player) {
        board.removePlayer(player);
        players.remove(player);
        System.out.println(player.getName() + "退出了游戏。");
    }

    public List<Player> getPlayers() {
        return players;
    }

    private boolean isGameOver() {
        return players.size() <= 1;
    }
}
