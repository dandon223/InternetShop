package com.company;

public class Item {
    private int id;
    private String name;
    private int cost;
    private int total;
    private int booked;

    public Item(int id,String name, int cost, int total, int booked) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.total = total;
        this.booked = booked;
    }

    public int getId() {
        return id;
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
