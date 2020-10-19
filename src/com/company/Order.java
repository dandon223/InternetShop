package com.company;

public class Order {
    private Item item;
    private Person person;
    private int howManyOrdered;

    public Order(Item item, Person person, int howManyOrdered) {
        this.item = item;
        this.person = person;
        this.howManyOrdered = howManyOrdered;
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
}
