package com.monopoly.Game.Squares;

import com.monopoly.Game.Board;
import com.monopoly.Game.Game;
import com.monopoly.Game.Player;
import com.monopoly.Game.Properties.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PropertySquare extends Square {
    private double landPrice;
    private Player owner;
    private TreeSet<Property> properties;
    private TreeSet<Property> availableProperties;
    private TreeSet<Property> occupiedProperties;

    public PropertySquare(String name, double landPrice, Game game, Board board) {
        super(name, game, board);
        this.landPrice = landPrice;
        this.owner = null;
        this.properties = new TreeSet<>();
        this.availableProperties = new TreeSet<>();
        this.occupiedProperties = new TreeSet<>();
        addProperty("房子", 100, 50);
        addProperty("别墅", 300, 200);
    }

    public void addProperty(String name, double price, double rent) {
        Property property = new Property(name, price, rent, this);
        property.setLocation(this);
        properties.add(property);
        availableProperties.add(property);
    }

    public void removeProperty(Property property) {
        properties.remove(property);
        if (availableProperties.contains(property)) {
            availableProperties.remove(property);
        } else {
            occupiedProperties.remove(property);
        }
    }

    public void passBy(Player player) {
        return;
    }

    private void buyLand(Player player) {
        assert owner == null;
        System.out.println(name + "地价为 " + landPrice + " 金钱。");
        System.out.print(player.getName() + "，你可以 (1) 购买" + name + "、(2) 不购买" + name + "。你的选择是 ");
        int option = Game.readOption(2);
        switch (option) {
            case 1:
                if (player.reduceCash(landPrice)) {
                    owner = player;
                    System.out.println("购买成功，你还拥有 " + player.getCash() + " 金钱。");
                } else {
                    System.out.println("购买失败。");
                }
            case 2:
                return;
        }

    }

    private void buyProperty(Player player) {
        assert owner == player;
        System.out.print(owner.getName() + "，你拥有" + name + "。");
        if (!availableProperties.isEmpty()) {
            System.out.println(name + "上有：");
            int j = 1;
            for (Property property : availableProperties) {
                System.out.print("(" + j + ") " + property.getName() + "，");
                System.out.println("价值 " + property.getPrice() + " 金钱。");
                j++;
            }
            System.out.print("你可以 (1--" + (j - 1) + ") 购买房屋、(" + j + ") 不购买房屋。你的选择是 ");
            int option = Game.readOption(j);
            List<Property> availablePropertiesList = new ArrayList<>(availableProperties);
            if (option < j) {
                Property property = availablePropertiesList.get(option - 1);
                if (player.reduceCash(property.getPrice())) {
                    property.setOwner(player);
                    availableProperties.remove(property);
                    occupiedProperties.add(property);
                    System.out.println("购买" + property.getName() + "成功。" + player.getName() + "，你支付了过房价。你还拥有 " + player.getCash() + " 金钱。");
                } else {
                    System.out.println("购买失败。");
                }
            }
        } else {
            System.out.println("你拥有所有房屋。");
        }
    }

    private void payRent(Player player) {
        assert owner != null && owner != player;
        System.out.print(owner.getName() + "拥有" + name + "，");
        double rentTotal = 0;
        for (Property property : occupiedProperties) {
            rentTotal += property.getRent();
        }
        System.out.println(name + "过路费为 " + rentTotal + "。");
        if (rentTotal > 0) {
            if (player.reduceCash(rentTotal)) {
                System.out.println(player.getName() + "，你支付了过路费。你还拥有 " + player.getCash() + " 金钱。");
            } else {
                System.out.println(player.getName() + "，你无法支付过路费，破产了！");
                player.exitGame();
            }
        }
    }

    public boolean landOn(Player player) {
        System.out.println(player.getName() + "来到了" + name + "。");
        if (owner == null) {
            buyLand(player);
        } else if (owner == player) {
            buyProperty(player);
        } else {
            payRent(player);
        }
        return true;
    }

    public void removePlayer(Player player) {
        if (owner == player) {
            for (Property property : occupiedProperties) {
                occupiedProperties.remove(property);
                availableProperties.add(property);
            }
            owner = null;
        }
    }
}
