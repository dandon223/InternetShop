package com.company.models;

/**
 * class that simulates person who has an account in a shop
 * @author Daniel
 */
public class Person {
    /**
     * unique id of a person
     */
    private int id;
    /**
     * last name of a person
     */
    private String lastName;
    /**
     * first name of a person
     */
    private String firstName;
    /**
     * either Staff or Customer
     */
    private String type;

    /**
     *
     * @param id id of person as is implemented in sql model
     * @param firstName first name of a peson
     * @param lastName last name of a person
     * @param type customer or staff
     */
    public Person(int id,String firstName, String lastName, String type) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.type = type;
    }

    /**
     * getter
     * @return last name of a person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getter
     * @return first name of a person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter
     * @return type of a person
     */
    public String getType() {
        return type;
    }

    /**
     * getter
     * @return id of a person
     */
    public int getId() {
        return id;
    }
}
