package com.company;

public class Item {
    private String name;
    private int cost;
    private int total;
    private int booked;

    public Item(String name, int cost, int total, int booked) {
        this.name = name;
        this.cost = cost;
        this.total = total;
        this.booked = booked;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getTotal() {
        return total;
    }

    public int getBooked() {
        return booked;
    }
}
