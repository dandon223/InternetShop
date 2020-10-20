package com.company;

/**
 * class that simulates customer order
 * @author Daniel
 */
public class Order {
    private int orderId;
    private Person person;
    private Item item;
    private int howManyOrdered;
    private  int howManyBought;

    /**
     *
     * @param orderId id of an order that is used in sql data
     * @param item Item object that was ordered
     * @param person Person object that ordered the item
     * @param howManyOrdered how many pieces of an item were ordered
     * @param howManyBought how many pieces of an item were bought, customer had them delivered
     */
    public Order(int orderId,Item item, Person person, int howManyOrdered, int howManyBought) {
        this.orderId = orderId;
        this.item = item;
        this.person = person;
        this.howManyOrdered = howManyOrdered;
        this.howManyBought = howManyBought;
    }

    /**
     * getter
     * @return order id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * getter
     * @return item object
     */
    public Item getItem() {
        return item;
    }

    /**
     * getter
     * @return person object
     */
    public Person getPerson() {
        return person;
    }

    /**
     * getter
     * @return how many ordered by that person
     */
    public int getHowManyOrdered() {
        return howManyOrdered;
    }

    /**
     * getter
     * @return how many bought by that person
     */
    public int getHowManyBought() {
        return howManyBought;
    }
}
