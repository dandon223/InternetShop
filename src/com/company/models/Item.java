package com.company.models;

/**
 * class that simulates item in shop
 * @author Daniel
 */
public class Item {
    private int id;
    private String name;
    private int cost;
    private int howManyLeft;

    /**
     *
     * @param id id of item in shop as is implemented in sql model
     * @param name name of a product
     * @param cost cost of a product
     * @param howManyLeft number of products in shop that can be still bought
     */
    public Item(int id,String name, int cost, int howManyLeft) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.howManyLeft = howManyLeft;
    }

    /**
     * getter
     * @return id of an item
     */
    public int getId() {
        return id;
    }

    /**
     * getter
     * @return name of an item
     */
    public String getName() {
        return name;
    }

    /**
     * getter
     * @return cost of an item
     */
    public int getCost() {
        return cost;
    }

    /**
     * getter
     * @return total number of items in shop
     */
    public int getHowManyLeft() {
        return howManyLeft;
    }
}
