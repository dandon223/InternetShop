package com.company;

public class Person {
    private String lastName;
    private String firstName;
    private String type;

    public Person(String firstName, String lastName, String type) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.type = type;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getType() {
        return type;
    }
}
