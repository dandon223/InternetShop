package com.company;

public class Person {
    private int id;
    private String lastName;
    private String firstName;
    private String type;

    public Person(int id,String firstName, String lastName, String type) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}
