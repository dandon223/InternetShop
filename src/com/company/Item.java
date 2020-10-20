package com.company;

/**
 * class that simulates item in shop
 * @author Daniel
 */
public class Item {
    private int id;
    private String name;
    private int cost;
    private int total;
    private int booked;

    /**
     *
     * @param id id of item in shop as is implemented in sql model
     * @param name name of a product
     * @param cost cost of a product
     * @param total total number of products in shop
     * @param booked number of booked products in shop
     */
    public Item(int id,String name, int cost, int total, int booked) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.total = total;
        this.booked = booked;
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
    public int getTotal() {
        return total;
    }

    /**
     * getter
     * @return booked number of items in shop
     */
    public int getBooked() {
        return booked;
    }
}
