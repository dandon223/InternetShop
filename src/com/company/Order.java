package com.company;

public class Order {
    private int orderId;
    private int itemId;
    private int personId;
    private int howManyOrdered;
    private  int howManyBought;

    public Order(int orderId,int itemId, int personId, int howManyOrdered, int howManyBought) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.personId = personId;
        this.howManyOrdered = howManyOrdered;
        this.howManyBought = howManyBought;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getHowManyOrdered() {
        return howManyOrdered;
    }

    public int getHowManyBought() {
        return howManyBought;
    }
}
