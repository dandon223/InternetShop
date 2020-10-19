package com.company;

public class Order {
    private int orderId;
    private Person person;
    private Item item;
    private int howManyOrdered;
    private  int howManyBought;

    public Order(int orderId,Item item, Person person, int howManyOrdered, int howManyBought) {
        this.orderId = orderId;
        this.item = item;
        this.person = person;
        this.howManyOrdered = howManyOrdered;
        this.howManyBought = howManyBought;
    }

    public int getOrderId() {
        return orderId;
    }

    public Item getItem() {
        return item;
    }

    public Person getPerson() {
        return person;
    }

    public int getHowManyOrdered() {
        return howManyOrdered;
    }

    public int getHowManyBought() {
        return howManyBought;
    }
}
