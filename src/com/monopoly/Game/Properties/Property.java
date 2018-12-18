package com.monopoly.Game.Properties;

import com.monopoly.Game.Player;
import com.monopoly.Game.Squares.Square;

public class Property implements Comparable<Property> {
    private String name;
    private double price;
    private double rent;
    private Square location;
    private Player owner;

    public Property(String name, double price, double rent, Square location) {
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.location = location;
        this.owner = null;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Property property) {
        return this.getName().compareTo(property.getName());
    }

    public Square getLocation() {
        return location;
    }

    public void setLocation(Square location) {
        this.location = location;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public double getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public double getRent() {
        return rent;
    }
}
